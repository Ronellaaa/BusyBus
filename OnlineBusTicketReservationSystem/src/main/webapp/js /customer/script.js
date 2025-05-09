
let currentBusIndex = 0;
let buses = [
	  {
	    name: "Super Line Express",
	    description: "Colombo to Kandy - Luxury A/C bus service for a smooth ride.",
	    image: "./assets/r2.png",
	    color: "linear-gradient(to right,rgb(90, 11, 2), #1f1c18)"
	  },
	  {
	    name: "Sangaraja Travels",
	    description: "Matara to Colombo - Smooth & comfy ride with Wi-Fi.",
	    image: "./assets/g2.png",
	    color: "linear-gradient(to right, #3e5151, #decba4)"
	  },
	  {
	    name: "Nawaloka Express",
	    description: "Travel to Jaffna with comfort and speed!",
	    image: "./assets/w1.png",
	    color: "linear-gradient(to right, #2c3e50, #bdc3c7)"
	  },
	  {
	    name: "Pothgul Kumari",
	    description: "The scenic ride to Nuwara Eliya – Enjoy the views.",
	    image: "./assets/bus4.png",
	    color: "linear-gradient(to right, #232526, #414345)"
	  },
	];



function changeBus(index) {

	
	
  const bus = buses[index];
  const image = document.getElementById("bus-image");
  image.style.transition = "transform 0.4s ease, opacity 0.4s ease";
  image.style.transform = "translateX(-100px)";
  image.style.opacity = "0";

  setTimeout(() => {
    image.src = bus.image;
    document.getElementById("bus-name").textContent = bus.name;
    document.getElementById("bus-description").textContent = bus.description;
    document.getElementById("step").textContent = `0${index + 1} / 04`;
    document.getElementById('bus-section').style.background = bus.color;

    image.style.transform = "translateX(100px)";
    setTimeout(() => {
      image.style.transition = "transform 0.4s ease, opacity 0.4s ease";
      image.style.transform = "translateX(0)";
      image.style.opacity = "1";
    }, 10);
  }, 400);

  document.querySelectorAll(".circle").forEach((c, i) => {
    c.classList.toggle("active", i === index);
  });
}


changeBus(0);

// ✅ Auto change every 2.5 seconds
setInterval(() => {
  currentBusIndex = (currentBusIndex + 1) % buses.length;
  changeBus(currentBusIndex);
}, 2500);

// ✅ GSAP SplitText ScrollTrigger
let split = SplitText.create(".split", { type: "chars" });

gsap.from(split.chars, {
  scrollTrigger: {
    trigger: ".split",
    start: "top 80%",
    toggleActions: "play none none none",
  },
  x: 150,
  opacity: 0,
  duration: 0.7,
  ease: "power4",
  stagger: 0.04,
});
