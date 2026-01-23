function saveUserSession(userLoginDto) {
    sessionStorage.setItem(
        "user",
        JSON.stringify(userLoginDto)
    );
}

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const memberIdentification =
            document.getElementById("memberIdentification").value;

        try {
            const user = await login(memberIdentification);

            if (!user) {
                alert("Usuario no encontrado");
                return;
            }

            saveUserSession(user);
            window.location.href = "index.html";
        } catch (error) {
            alert("Error al intentar ingresar");
        }
    });
});

function showTab(tabName) {
    document.querySelectorAll(".tab-content").forEach(tab => {
        tab.classList.add("hidden");
    });

    document.getElementById(`tab-${tabName}`).classList.remove("hidden");
}

function logout() {
    sessionStorage.removeItem("user");
    location.reload();
}
