async function apiFetch(url, options = {}) {
    const response = await fetch(url, {
        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        },
        ...options
    });

    let data = null;
    try {
        data = await response.json();
    } catch (_) {}

    if (!response.ok) {
        throw new Error(data?.message || "Error inesperado");
    }

    return data;
}
