
console.log("api.js cargado");
const API_BASE_URL = "http://localhost:8080/api";

async function getProducts() {
    const response = await fetch(`${API_BASE_URL}/products`);

    if (!response.ok) {
        throw new Error("Error al obtener productos");
    }

    return response.json();
}

async function getSession() {
    const response = await fetch(`${API_BASE_URL}/`)
}

async function login(memberIdentification) {
    try {
        const response = await fetch(`${API_BASE_URL}/users/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ memberIdentification })
            });

            if (!response.ok) {
                alert("Usuario no encontrado");
                return;
            }

            return response.json();

    } catch (error) {
        console.error("Login error:", error);
        alert("Error al intentar ingresar");
    }
}

