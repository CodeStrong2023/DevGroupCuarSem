import { Input } from "../components/ui/Input.jsx";
import { Card } from "../components/ui/Card.jsx";
import { Button } from "../components/ui/Button.jsx";
import { useForm } from "react-hook-form";

function RegisterPage() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = handleSubmit((data) => {
    console.log(data);
  });

  // console.log(errors);

  return (
    <div className="h-[calc(100vh-64px)] flex items-center justify-center">
      <Card>
        <h3 className="text-2xl font-bold text-blue-500">Registro</h3>

        <form onSubmit={onSubmit}>
          <Input
            placeholder="Ingrese su nombre"
            {...register("name", { required: true })}
          ></Input>

          {errors.name && (
            <p className="text-red-500">Este campo es requerido</p>
          )}

          <Input
            type="email"
            placeholder="Ingrese su email"
            {...register("email", { required: true })}
          ></Input>

          {errors.email && (
            <p className="text-red-500">Este campo es requerido</p>
          )}

          <Input
            type="password"
            placeholder="Ingrese su contrasena"
            {...register("password", { required: true })}
          ></Input>

          {errors.password && (
            <p className="text-red-500">Este campo es requerido</p>
          )}

          <Button>Registrarse</Button>
        </form>
      </Card>
    </div>
  );
}

export default RegisterPage;