document.addEventListener("DOMContentLoaded", () => {
    const userRaw = sessionStorage.getItem("user");

    const loginContainer = document.getElementById("login-container");
    const logoutContainer = document.getElementById("logout-container");

    const adminTab = document.querySelector('[data-tab="admin"]');
    const checkoutTab = document.querySelector('[data-tab="checkout"]');

    const logoutBtn = document.getElementById("logoutBtn");

    // --- LOGIN / LOGOUT ---
    if (userRaw) {
        loginContainer.style.display = "none";
        logoutContainer.style.display = "block";
    } else {
        loginContainer.style.display = "block";
        logoutContainer.style.display = "none";
    }

    // --- CONTROL DE TABS POR ROL ---
    if (!userRaw) {
        if (adminTab) adminTab.style.display = "none";
    } else {
        const user = JSON.parse(userRaw);

        if (user.type === "ADMIN") {
            if (checkoutTab) checkoutTab.style.display = "none";
        } else {
            if (adminTab) adminTab.style.display = "none";
        }
    }

    // --- LOGOUT ---
    if (logoutBtn) {
        logoutBtn.addEventListener("click", logout);
    }
});

document.addEventListener("DOMContentLoaded", () => {
    const user = JSON.parse(sessionStorage.getItem("user"));
    if (user && user.type === "ADMIN") {
        initAdmin();
    }
});
