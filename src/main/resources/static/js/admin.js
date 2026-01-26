function showAdminSection(section) {
    document.querySelectorAll(".admin-section")
        .forEach(div => div.classList.add("hidden"));

    document.getElementById(`admin-${section}`)
        .classList.remove("hidden");

    if (section === "products") loadAdminProducts();
    if (section === "users") loadAdminUsers();
    if (section === "discounts") loadAdminDiscounts();
}


function renderAdminProducts(products) {
    const list = document.getElementById("adminProductList");
    list.innerHTML = "";

    products.forEach(p => {
        const li = document.createElement("li");
        li.className = "flex justify-between items-center py-2";

        li.innerHTML = `
            <span>${p.name} - $${p.price}</span>
            <div class="flex gap-2">
                <button class="bg-green-500 text-white px-3 py-1 rounded">
                    Modificar
                </button>
                <button class="bg-red-500 text-white px-3 py-1 rounded">
                    Eliminar
                </button>
            </div>
        `;

        list.appendChild(li);
    });
}

function initAdmin() {
    showAdminSection("products");
    loadAdminProducts();
}

async function loadAdminProducts() {
    const list = document.getElementById("adminProductList");
    list.innerHTML = "";

    const products = await getAllProducts();

    products.forEach(p => {
        const li = document.createElement("li");
        li.className = "grid grid-cols-6 gap-2 p-2 text-sm items-center";

        li.innerHTML = `
            <span>${p.id}</span>
            <span>${p.name}</span>
            <span>$${p.price}</span>
            <span>${p.categoryName}</span>
            <span>${p.active ? "✔️" : "❌"}</span>
            <div class="flex gap-2">
                <button class="text-blue-600">Edit</button>
                <button class="text-red-600">Delete</button>
            </div>
        `;

        list.appendChild(li);
    });
}

async function loadAdminUsers() {
    const list = document.getElementById("adminUserList");
    list.innerHTML = "";

    const users = await getAllUsers();

    users.forEach(u => {
        const li = document.createElement("li");
        li.className = "grid grid-cols-8 gap-2 p-2 text-sm";

        li.innerHTML = `
            <span>${u.id}</span>
            <span>${u.name}</span>
            <span>${u.surname}</span>
            <span>${u.memberIdentification}</span>
            <span>${u.celphone}</span>
            <span>${u.email}</span>
            <span>${u.type}</span>
            <span>${u.active ? "✔️" : "❌"}</span>
        `;

        list.appendChild(li);
    });
}

async function loadAdminDiscounts() {
    const list = document.getElementById("adminDiscountList");
    list.innerHTML = "";

    const discounts = await getAllDiscounts();

    discounts.forEach(d => {
        const li = document.createElement("li");
        li.className = "grid grid-cols-6 gap-2 p-2 text-sm";

        li.innerHTML = `
            <span>${d.id}</span>
            <span>${d.discountType}</span>
            <span>${d.productId ?? "-"}</span>
            <span>${d.percentage ?? "-"}</span>
            <span>${d.active ? "✔️" : "❌"}</span>
            <div class="flex gap-2">
                <button class="text-blue-600">Edit</button>
                <button class="text-red-600">Delete</button>
            </div>
        `;

        list.appendChild(li);
    });
}

