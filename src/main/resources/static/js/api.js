const API_BASE_URL = "http://localhost:8080/api";

async function getProducts() {
    const response = await fetch(`${API_BASE_URL}/products/actives`);

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
        memberIdentification = memberIdentification.trim();

        const response = await fetch(`${API_BASE_URL}/users/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ memberIdentification })
            });

            if (!response.ok) {
                const error = await response.json();

                if (response.status === 400) {
                    alert(error.message);
                } else if (response.status === 404) {
                    alert("Usuario no encontrado");
                } else {
                    alert("Error inesperado");
                }
                return;
            }


            return response.json();

    } catch (error) {
        console.error("Login error:", error);
        alert("Error al intentar ingresar");
    }
}

async function getAllProducts() {
    const response = await fetch(`${API_BASE_URL}/products`);
    if (!response.ok) throw new Error("Error al obtener productos");
    return response.json();
}

async function getAllUsers() {
    const response = await fetch(`${API_BASE_URL}/users`);
    if (!response.ok) throw new Error("Error al obtener usuarios");
    return response.json();
}

async function getAllDiscounts() {
    const response = await fetch(`${API_BASE_URL}/discounts`);
    if (!response.ok) throw new Error("Error al obtener descuentos");
    return response.json();
}

async function getAllCategories() {
    const response = await fetch(`${API_BASE_URL}/categories`);
    if (!response.ok) throw new Error("Error al obtener categorias");
    return response.json();
}


