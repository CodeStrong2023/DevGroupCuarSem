import pkg from 'pg';
const { Pool } = pkg;


export const pool = new Pool({
  port: 5432,
  host: "localhost",
  user: "postgres",
  password: "Escandinava",
  database: "PERN",
});

pool.on("connect", () => {
  console.log("Connected to the db");
});
