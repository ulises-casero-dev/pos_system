document.addEventListener("DOMContentLoaded", () => {
    const user = sessionStorage.getItem("user");

    const loginContainer = document.getElementById("login-container");
    const logoutContainer = document.getElementById("logout-container");
    const adminTab = document.getElementById("tab-admin");
    const logoutBtn = document.getElementById("logoutBtn");

    // --- LOGIN / LOGOUT ---
    if (user) {
        loginContainer.style.display = "none";
        logoutContainer.style.display = "block";
    } else {
        loginContainer.style.display = "block";
        logoutContainer.style.display = "none";
    }

    // --- ADMIN TAB ---
    if (!user) {
        adminTab.style.display = "none";
    } else {
        const parsedUser = JSON.parse(user);
        if (parsedUser.type !== "ADMIN") {
            adminTab.style.display = "none";
        }
    }

    // --- LOGOUT ---
    if (logoutBtn) {
        logoutBtn.addEventListener("click", logout);
    }
});
