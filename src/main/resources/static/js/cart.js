let cart = [];

function addToCart(product) {
    const existing = cart.find(p => p.id === product.id);

    if (existing) {
        existing.quantity++;
    } else {
        cart.push({
            ...product,
            quantity: 1
        });
    }

    renderCart();
}

function renderCart() {
    const cartList = document.getElementById("cartList");
    const totalAmount = document.getElementById("totalAmount");

    cartList.innerHTML = "";
    let total = 0;

    cart.forEach(item => {
        const subtotal = item.price * item.quantity;
        total += subtotal;

        const li = document.createElement("li");
        li.className = "flex justify-between items-center border p-2 rounded";

        li.innerHTML = `
            <div>
                <strong>${item.name}</strong><br>
                $${item.price} x
                <input
                    type="number"
                    min="1"
                    value="${item.quantity}"
                    class="w-16 border rounded px-1"
                    onchange="updateQuantity(${item.id}, this.value)"
                >
                = $${subtotal}
            </div>

            <button
                class="bg-red-600 text-white px-2 py-1 rounded hover:bg-red-700"
                onclick="removeFromCart(${item.id})"
            >
                Quitar
            </button>
        `;

        cartList.appendChild(li);
    });

    totalAmount.textContent = total;
}

function updateQuantity(productId, quantity) {
    const item = cart.find(p => p.id === productId);
    item.quantity = parseInt(quantity);
    renderCart();
}

function removeFromCart(productId) {
    cart = cart.filter(p => p.id !== productId);
    renderCart();
}
