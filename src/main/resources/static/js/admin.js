function showAdminSection(section) {
    document.querySelectorAll(".admin-section")
        .forEach(div => div.classList.add("hidden"));

    document.getElementById(`admin-${section}`)
        .classList.remove("hidden");

    if (section === "products") loadAdminProducts();
    if (section === "users") loadAdminUsers();
    if (section === "discounts") loadAdminDiscounts();
    if (section === "categories") loadAdminCategories();
}

function initAdmin() {
    showAdminSection("products");
    loadAdminProducts();
}


// Produtcs
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("productForm");
    if (!form) return;

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const id = document.getElementById("productId").value;
        const categoryValue = document.getElementById("productCategory").value;

        const payload = {
            name: document.getElementById("productName").value,
            price: document.getElementById("productPrice").value
        };

        if (categoryValue) {
            payload.categoryId = Number(categoryValue);
        }

        const url = id
            ? `${API_BASE_URL}/products/${id}`
            : `${API_BASE_URL}/products`;

        const method = id ? "PATCH" : "POST";

        try {
            await apiFetch(url, {
                method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });

            console.log(typeof apiFetch);

            closeProductModal();
            loadAdminProducts();

        } catch (error) {
            alert(error.message);
        }
    });
});

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
                <a href="#" class="text-blue-600" onclick='openEditProductModal(${JSON.stringify(p)})'>Edit</a>
                ${
                    p.active
                        ? `<button class="text-red-600" onclick="deactivateProduct(${p.id})">Delete</button>`
                        : `<button class="text-green-600" onclick="activateProduct(${p.id})">Activate</button>`
                }
            </div>
        `;

        list.appendChild(li);
    });
}

const modal = document.getElementById("productModal");
const modalTitle = document.getElementById("modalTitle");
const productForm = document.getElementById("productForm");

function openCreateProductModal() {
    loadCategories();
    modalTitle.textContent = "Nuevo producto";
    productForm.reset();
    document.getElementById("productId").value = "";
    document.getElementById("productCategory").required = true;
    modal.classList.remove("hidden");
}

function openEditProductModal(product) {
    modalTitle.textContent = "Editar producto";

    document.getElementById("productId").value = product.id;
    document.getElementById("productName").value = product.name;
    document.getElementById("productPrice").value = product.price;

    const categorySelect = document.getElementById("productCategory");
        categorySelect.required = false;

    categorySelect.value = product.categoryId ?? "";

    modal.classList.remove("hidden");
}

function closeProductModal() {
    modal.classList.add("hidden");
}

async function loadCategories() {
    const res = await fetch(`${API_BASE_URL}/categories/actives`);
    const categories = await res.json();

    const select = document.getElementById("productCategory");
    select.innerHTML = '<option value="">Seleccione categoría</option>';

    categories.forEach(c => {
        const option = document.createElement("option");
        option.value = c.id;        // 👈 ID REAL
        option.textContent = c.name;
        select.appendChild(option);
    });
}

async function deactivateProduct(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/products/deactivate/${id}`,
            { method: "PATCH" }
        );

        loadAdminCategories();

    } catch (error) {
        alert(error.message);
    }
}

async function activateProduct(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/products/activate/${id}`,
            { method: "PATCH" }
        );

        loadAdminCategories();

    } catch (error) {
        alert(error.message);
    }
}


// Users
async function loadAdminUsers() {
    const list = document.getElementById("adminUserList");
    list.innerHTML = "";

    const users = await getAllUsers();

    users.forEach(u => {
        const li = document.createElement("li");
        li.className = "grid grid-cols-[60px_1fr_1fr_1fr_1fr_2fr_1fr_80px_120px] gap-2 p-2 text-sm items-center";

        li.innerHTML = `
            <span>${u.id}</span>
            <span>${u.name}</span>
            <span>${u.surname}</span>
            <span>${u.memberIdentification}</span>
            <span>${u.celphone}</span>
            <span class="truncate overflow-hidden whitespace-nowrap">
                ${u.email ?? "-"}
            </span>
            <span>${u.type}</span>
            <span>${u.active ? "✔️" : "❌"}</span>
            <div class="flex gap-2">
                <a href="#" class="text-blue-600" onclick='openEditUserModal(${JSON.stringify(u)})'>Edit</a>
                ${
                    u.active
                        ? `<button class="text-red-600" onclick="deactivateUser(${u.id})">Delete</button>`
                        : `<button class="text-green-600" onclick="activateUser(${u.id})">Activate</button>`
                }
            </div>
        `;

        list.appendChild(li);
    });
}

const userModal = document.getElementById("userModal");
const userForm = document.getElementById("userForm");

function openCreateUserModal() {
    document.getElementById("userModalTitle").textContent = "Nuevo usuario";
    userForm.reset();
    document.getElementById("userId").value = "";

    const ciInput = document.getElementById("userCi");
    ciInput.disabled = false;
    ciInput.required = true;

    userModal.classList.remove("hidden");
}

function openEditUserModal(user) {
    document.getElementById("userModalTitle").textContent = "Editar usuario";

    userId.value = user.id;

    userName.value = user.name;
    userSurname.value = user.surname;
    userEmail.value = user.email;
    userPhone.value = user.celphone ?? "";
    userCi.value = user.memberIdentification;
    userType.value = user.type;

    const ciInput = document.getElementById("userCi");
    ciInput.disabled = true;
    ciInput.required = false;

    document.getElementById("userName").required = false;
    document.getElementById("userSurname").required = false;
    document.getElementById("userEmail").required = false;
    document.getElementById("userType").required = false;

    userModal.classList.remove("hidden");
}


function closeUserModal() {
    userModal.classList.add("hidden");
}

userForm.addEventListener("submit", async e => {
    e.preventDefault();

    const id = userId.value;

    const payload = {
        name: userName.value,
        surname: userSurname.value,
        email: userEmail.value,
        celphone: userPhone.value,
        identification: userCi.value,
        userType: userType.value
    };

    const url = id
        ? `${API_BASE_URL}/users/${id}`
        : `${API_BASE_URL}/users`;

    const method = id ? "PUT" : "POST";

    try {
        await apiFetch(url, {
            method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        console.log(typeof apiFetch);

        closeUserModal();
        loadAdminUsers();

    } catch (error) {
        alert(error.message);
    }
});

async function deactivateUser(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/users/deactivate/${id}`,
            { method: "PATCH" }
        );

        loadAdminUsers();

    } catch (error) {
        alert(error.message);
    }
}

async function activateUser(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/users/activate/${id}`,
            { method: "PATCH" }
        );

        loadAdminUsers();

    } catch (error) {
        alert(error.message);
    }
}

// Discounts
async function loadAdminDiscounts() {
    const list = document.getElementById("adminDiscountList");
    list.innerHTML = "";

    const discounts = await getAllDiscounts();

    discounts.forEach(d => {
        const li = document.createElement("li");
        li.className = "grid grid-cols-[60px_2fr_1fr_1fr_1fr_1fr_80px_120px] gap-2 text-sm border-b pb-2 mb-2 items-center";

        li.innerHTML = `
            <span>${d.id}</span>
            <span class="truncate overflow-hidden whitespace-nowrap">
                ${d.description}
            </span>
            <span>${d.discountType}</span>
            <span>${d.productId ?? "-"}</span>
            <span>${d.amount}</span>
            <span>${d.limitAmount}</span>
            <span>${d.active ? "✔️" : "❌"}</span>
            <div class="flex gap-2">
                <a href="#" class="text-blue-600" onclick='openEditDiscountModal(${JSON.stringify(d)})'>Edit</a>
                ${
                    d.active
                        ? `<button class="text-red-600" onclick="deactivateDiscount(${d.id})">Delete</button>`
                        : `<button class="text-green-600" onclick="activateDiscount(${d.id})">Activate</button>`
                }
            </div>
        `;


        list.appendChild(li);
    });
}

const discountModal = document.getElementById("discountModal");
const discountForm = document.getElementById("discountForm");
const discountModalTitle = document.getElementById("discountModalTitle");

function openCreateDiscountModal() {
    discountModalTitle.textContent = "Nuevo descuento";
    discountForm.reset();
    document.getElementById("discountId").value = "";
    discountModal.classList.remove("hidden");
}

function openEditDiscountModal(discount) {
    discountModalTitle.textContent = "Editar descuento";

    document.getElementById("discountId").value = discount.id;
    document.getElementById("discountDescription").value = discount.description;
    document.getElementById("discountAmount").value = discount.amount;
    document.getElementById("discountType").value = discount.discountType;
    document.getElementById("discountLimitAmount").value = discount.limitAmount ?? "";
    document.getElementById("discountProductId").value = discount.productId ?? "";

    discountModal.classList.remove("hidden");
}

function closeDiscountModal() {
    discountModal.classList.add("hidden");
}

async function deactivateDiscount(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/discounts/deactivate/${id}`,
            { method: "PATCH" }
        );

        loadAdminDiscounts();

    } catch (error) {
        alert(error.message);
    }
}

async function activateDiscount(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/discounts/activate/${id}`,
            { method: "PATCH" }
        );

        loadAdminDiscounts();

    } catch (error) {
        alert(error.message);
    }
}

discountForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.getElementById("discountId").value;

    const payload = {
        description: document.getElementById("discountDescription").value,
        amount: document.getElementById("discountAmount").value,
        discountType: document.getElementById("discountType").value,
        limitAmount: document.getElementById("discountLimitAmount").value || null,
    };

    if (productIdValue !== "") {
        payload.productId = Number(productIdValue);
    }

    console.log("Payload enviado:", payload);

    const url = id
        ? `${API_BASE_URL}/discounts/${id}`
        : `${API_BASE_URL}/discounts`;

    const method = id ? "PATCH" : "POST";

    try {
        await apiFetch(url, {
            method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        closeDiscountModal();
        loadAdminDiscounts();

    } catch (error) {
        alert(error.message);
    }
});

// Categories
async function loadAdminCategories() {
    const list = document.getElementById("adminCategoryList");
    list.innerHTML = "";

    const categories = await getAllCategories();

    categories.forEach(c => {
        const li = document.createElement("li");
        li.className = "grid grid-cols-6 gap-2 p-2 text-sm";

        li.innerHTML = `
            <span>${c.id}</span>
            <span>${c.name}</span>
            <span>${c.active ? "✔️" : "❌"}</span>
            <div class="flex gap-2">
                <a href="#" class="text-blue-600" onclick='openEditCategoryModal(${JSON.stringify(c)})'>Edit</a>
                ${
                    c.active
                        ? `<button class="text-red-600" onclick="deactivateCategory(${c.id})">Delete</button>`
                        : `<button class="text-green-600" onclick="activateCategory(${c.id})">Activate</button>`
                }
            </div>
        `;


        list.appendChild(li);
    });
}

const categoryModal = document.getElementById("categoryModal");
const categoryForm = document.getElementById("categoryForm");
const categoryModalTitle = document.getElementById("categoryModalTitle");

function openCreateCategoryModal() {
    categoryModalTitle.textContent = "Nueva categoría";
    categoryForm.reset();
    document.getElementById("categoryId").value = "";
    categoryModal.classList.remove("hidden");
}


function openEditCategoryModal(category) {
    categoryModalTitle.textContent = "Editar categoría";
    document.getElementById("categoryName").value = category.name;
    categoryModal.classList.remove("hidden");
}

function closeCategoryModal() {
    categoryModal.classList.add("hidden");
}

async function deactivateCategory(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/categories/deactivate/${id}`,
            { method: "PATCH" }
        );

        loadAdminCategories();

    } catch (error) {
        alert(error.message);
    }
}


async function activateCategory(id) {
    try {
        await apiFetch(
            `${API_BASE_URL}/categories/activate/${id}`,
            { method: "PATCH" }
        );

        loadAdminCategories();

    } catch (error) {
        alert(error.message);
    }
}

categoryForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.getElementById("categoryId").value;

    const payload = {
        name: document.getElementById("categoryName").value,

    };

    const url = id
        ? `${API_BASE_URL}/categories/${id}`
        : `${API_BASE_URL}/categories`;

    const method = id ? "PATCH" : "POST";

    try {
        await apiFetch(url, {
            method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        console.log(typeof apiFetch);

        closeCategoryModal();
        loadAdminCategories();

    } catch (error) {
        alert(error.message);
    }
});