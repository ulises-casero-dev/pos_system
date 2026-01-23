
document.addEventListener("DOMContentLoaded", async () => {
    try {
        const products = await getProducts();
        renderProducts(products);
    } catch (error) {
        console.error("Error cargando productos:", error);
    }
});

function renderProducts(products) {
    const productList = document.getElementById("productList");
    productList.innerHTML = "";

    products.forEach(product => {
        const li = document.createElement("li");
        li.className = "flex justify-between items-center border p-2 rounded";

        li.innerHTML = `
            <span>${product.name} - $${product.price}</span>
            <button
                class="bg-green-600 text-white px-3 py-1 rounded hover:bg-green-700"
                onclick='addToCart(${JSON.stringify(product)})'
            >
                Add
            </button>
        `;

        productList.appendChild(li);
    });
}

