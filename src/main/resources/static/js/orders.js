async function createOrder() {
    const userRaw = sessionStorage.getItem("user");
    const user = userRaw ? JSON.parse(userRaw) : null;

    if (cart.length === 0) {
        alert("El carrito está vacío");
        return;
    }

    const payload = {
        userId: user ? user.id : null,
        orderItems: cart.map(item => ({
            productId: item.id,
            quantity: item.quantity
        }))
    };

    try {
        const response = await fetch("http://localhost:8080/api/orders", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error("Error al crear la orden");
        }

        alert("Compra realizada con éxito");

        cart = [];
        renderCart();

    } catch (error) {
        console.error(error);
        alert("Error al confirmar la compra");
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const confirmBtn = document.getElementById("confirmOrderBtn");
    if (confirmBtn) {
        confirmBtn.addEventListener("click", createOrder);
    }
});