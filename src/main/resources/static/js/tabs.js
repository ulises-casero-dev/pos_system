document.addEventListener("DOMContentLoaded", () => {
    const tabs = document.querySelectorAll("[data-tab]");
    const contents = document.querySelectorAll(".tab-content");

    tabs.forEach(tab => {
        tab.addEventListener("click", () => {
            const selectedTab = tab.dataset.tab;

            // 1️⃣ Ocultar todo el contenido
            contents.forEach(content => {
                content.classList.add("hidden");
            });

            // 2️⃣ Mostrar el contenido del tab seleccionado
            const activeContent = document.getElementById(`tab-${selectedTab}`);
            if (activeContent) {
                activeContent.classList.remove("hidden");
            }

            // 3️⃣ Resetear estilos de todos los tabs
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
});
