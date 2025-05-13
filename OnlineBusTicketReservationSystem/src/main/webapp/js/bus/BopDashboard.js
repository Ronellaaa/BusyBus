document.addEventListener("DOMContentLoaded", function () {
  // Set the current date dynamically
  const today = new Date();
  const options = { year: "numeric", month: "long", day: "numeric" };
  document.getElementById("current-date").textContent = today.toLocaleDateString("en-US", options);

  const navItems = document.querySelectorAll(".nav-item");
  const headerOffset = 90; // Offset to account for fixed header height

  // Smooth scroll functionality for hash-based navigation only
  navItems.forEach((item) => {
    item.addEventListener("click", function (event) {
      const href = this.getAttribute("href");

      // If it's NOT a hash link, let the browser handle it normally
      if (!href.startsWith("#")) {
        return;
      }

      // Prevent default jump for in-page scrolling
      event.preventDefault();

      // Remove 'active' class from all items and add to the clicked one
      navItems.forEach((nav) => nav.classList.remove("active"));
      this.classList.add("active");

      // Scroll to the target section
      const targetId = href.substring(1); // Remove the '#' from href
      const targetSection = document.getElementById(targetId);

      if (targetSection) {
        const y = targetSection.getBoundingClientRect().top + window.pageYOffset - headerOffset;
        window.scrollTo({ top: y, behavior: "smooth" });
      }
    });
  });

  // Auto-scroll to section if URL has hash on page load
  const currentHash = window.location.hash;
  if (currentHash) {
    const initialNavItem = document.querySelector(`.nav-item[href='${currentHash}']`);
    const targetSection = document.querySelector(currentHash);

    if (initialNavItem && targetSection) {
      initialNavItem.classList.add("active");
      setTimeout(() => {
        const y = targetSection.getBoundingClientRect().top + window.pageYOffset - headerOffset;
        window.scrollTo({ top: y, behavior: "smooth" });
      }, 100);
    }
  }

  // Scroll to section if user navigates via browser history (back/forward)
  window.addEventListener('hashchange', function () {
    const newHash = window.location.hash;
    const targetSection = document.querySelector(newHash);

    if (targetSection) {
      const y = targetSection.getBoundingClientRect().top + window.pageYOffset - headerOffset;
      window.scrollTo({ top: y, behavior: "smooth" });
    }
  });
});


window.onload = function() {
    var successMessage = document.querySelector('.popup');
    console.log('Success Message Element:', successMessage);
    if (successMessage) {
        successMessage.classList.add('show');
        setTimeout(function() {
            successMessage.classList.remove('show');
        }, 3000);
    }
}

function showLogoutModal() {
    document.getElementById("logoutModal").style.display = "flex";
  }

  function closeLogoutModal() {
    document.getElementById("logoutModal").style.display = "none";
  }

  function confirmLogout() {
    // Redirect to homepage or login page
    window.location.href = "BopLogoutServlet"; // Change this to your actual homepage file if different
  }

  // Optional: Close modal if clicked outside
  window.onclick = function(event) {
    const modal = document.getElementById("logoutModal");
    if (event.target === modal) {
      closeLogoutModal();
    }
  }

