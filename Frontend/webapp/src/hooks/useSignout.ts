import { useState } from "react";
import { signout } from "../services/auth-service";
import { useAuthContext } from "./useAuthContext";

export const useSignout = () => {
    const { updateAuth } = useAuthContext();
    const [error, setError] = useState<string>("");
    const [isLoading, setLoader] = useState<boolean>(false);
    
    const logout = () => {
        setLoader(true);
      signout()
        .then((response) => {
          if (response && response.status === 204) {
            localStorage.clear();
            updateAuth(false);
          }
        })
        .catch((error) => setError(error.message))
        .finally(() => setLoader(false));
    }
    return {logout};
}