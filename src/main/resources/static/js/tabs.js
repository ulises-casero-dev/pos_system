document.addEventListener("DOMContentLoaded", () => {
    const tabs = document.querySelectorAll("[data-tab]");
    const contents = document.querySelectorAll(".tab-content");

    tabs.forEach(tab => {
        tab.addEventListener("click", () => {
            const selectedTab = tab.dataset.tab;

            // 1️⃣ Ocultar todos los contenidos
            contents.forEach(content => {
                content.classList.add("hidden");
            });

            // 2️⃣ Mostrar el contenido activo
            const activeContent = document.getElementById(`tab-${selectedTab}`);
            if (activeContent) {
                activeContent.classList.remove("hidden");
            }

            // 3️⃣ Resetear estilos
            tabs.forEach(t => {
                t.classList.remove(
                    "border-b-2",
                    "border-blue-500",
                    "text-blue-600"
                );
                t.classList.add("text-gray-500");
            });

            // 4️⃣ Marcar tab activo
            tab.classList.add(
                "border-b-2",
                "border-blue-500",
                "text-blue-600"
            );
            tab.classList.remove("text-gray-500");
        });
    });

    // Tab por defecto
    const defaultTab = document.querySelector('[data-tab="user"]');
    if (defaultTab) {
        defaultTab.click();
    }
});
