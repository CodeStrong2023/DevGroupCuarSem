import { pool } from "../db.js";
import bcrypt from "bcrypt";
import { createAccesToken } from "../libs/jwt.js";

export const signin = (req, res) => res.send("ingresando");
export const signup = async (req, res) => {
  const { name, email, password } = req.body;
  res.send("Registrando");

  try {
    const hashedPassword = await bcrypt.hash(password, 10);

    const result = await pool.query(
      "INSERT INTO usuarios (name, email, password) VALUES ($1,$2,$3) RETURNING *",
      [name, email, hashedPassword]
    );

    const token = await createAccesToken({ id: result.rows[0].id });
    res.cookie("token", token, {
      httpOnly: true,
      sameSite: "none",
      maxAge: 60 * 60 * 24 * 1000,
    });

    // return res.json(result.rows[0]);
    return res.json({ token: token });
  } catch (error) {
    if (error.code === "23505") {
      return res.status(400).json({ message: "El correo ya esta registrado" });
    }
  }
};
export const sigout = (req, res) => res.send("cerrando sesión");
export const profile = (req, res) => res.send("pefil de usuario");
