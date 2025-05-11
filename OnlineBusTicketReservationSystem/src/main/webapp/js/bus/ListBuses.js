function confirmDelete(busId) {
    const hiddenInput = document.getElementById("bus_id");
    const modal = document.getElementById("deleteModal");

    hiddenInput.value = busId;
    modal.style.display = "flex";
}
function closeModal() {
    document.getElementById("deleteModal").style.display = "none";
}

window.onclick = function(event) {
    const modal = document.getElementById("deleteModal");
    if (event.target === modal) {
        closeModal();
    }
};
