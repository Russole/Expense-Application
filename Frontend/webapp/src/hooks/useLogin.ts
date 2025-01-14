import { useState } from "react";
import { authenticate } from "../services/auth-service";
import { AuthRequest } from "../model/AuthRequest";
import { useNavigate } from "react-router-dom";

export const useLogin = () => {
    const [error, setError] = useState<string>("");
    const [isLoading, setLoader] = useState<boolean>(false);
    const navigator = useNavigate();
    const login = (authRequest: AuthRequest) => {
        setLoader(true);
      authenticate(authRequest)
        .then((response) => {
          console.log(response)
          localStorage.setItem("user", JSON.stringify(response.data));
          navigator("/");
        })
        .catch((error) => {
          console.log(error)
          setError(error.message);
        })
        .finally(() => setLoader(false));
    }
    return {error, isLoading, login};
}