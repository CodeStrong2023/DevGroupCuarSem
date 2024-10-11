import Router from "express-promise-router";
import {
  signin,
  signup,
  sigout,
  profile,
} from "../controllers/auth.controller.js";

const router = Router();

router.post("/signin", signin);

router.post("/signup", signup);

router.post("/sigout", sigout);

router.get("/profile", profile);

export default router;
