// Confirm delete function for the modal
function confirmDelete(busId) {
    const hiddenInput = document.getElementById("bus_id");
    const modal = document.getElementById("deleteModal");

    hiddenInput.value = busId; // Assign the busId to hidden input
    modal.style.display = "flex"; // Display modal
}

// Close modal function
function closeModal() {
    const modal = document.getElementById("deleteModal");
    modal.style.display = "none"; // Hide the modal
}

// Close modal when clicking outside
window.onclick = function(event) {
    const modal = document.getElementById("deleteModal");
    if (event.target === modal) {
        closeModal(); // Close modal if clicked outside
    }
};

// Auto-dismiss success popup after 3 seconds
window.onload = function() {
    var successMessage = document.querySelector('.popup');
    if (successMessage) {
        successMessage.classList.add('show'); // Make the popup visible
        setTimeout(function() {
            successMessage.classList.remove('show'); // Remove visibility after 3s
        }, 3000);
    }
};
