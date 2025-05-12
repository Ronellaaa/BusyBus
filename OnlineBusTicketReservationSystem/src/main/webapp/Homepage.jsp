<%@ page session="true" %>
<%
    String firstName = (String) session.getAttribute("name");
%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>BusExpress Journey</title>
    <link rel="stylesheet" href="styles/customer/homepage.css" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
    />

    <!-- <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"
    /> -->

    <script
      src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs"
      type="module"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
  </head>
  <body>
  
  
    <div class="home">
   
 <jsp:include page="/Navbar.jsp"/>

      <!-- 🔹 SECTION 1: HERO VIDEO + SEARCH -->
      <section class="hero-video-section1">
        <video autoplay muted loop playsinline class="hero-video">
      <source src="<%= request.getContextPath() %>/assets/b1.mp4" type="video/mp4" />
        </video>

        <div class="overlay-text">
          <div class="text-animation-container">
            <div class="text2 split">
        
    
              Ride Smart. Travel Easy. Reserve Your Seat Today 🌍 
            </div>
          </div>
          <div class="ovalay-1">
            <h2>Fast, Easy, Bookings</h2>
          <form action="book-seat" method="get">
             <input type="hidden" name="busId" value="1" />
             <button type="submit"  class="cta-button">Book Your seat <%= session.getAttribute("name") %>!</button>
            </form>
            <div class="search-wrapper">
              <div class="search-tab">
                <span
                  >📢 Search Through Over 7,500 Cities & Villages in Sri
                  Lanka</span
                >
              </div>
              <div class="search-bar">
                <select>
                  <option selected disabled>From</option>
                  <option>Colombo</option>
                  <option>Kandy</option>
                </select>
                <select>
                  <option selected disabled>To</option>
                  <option>Jaffna</option>
                  <option>Galle</option>
                </select>
                <input type="date" />
                <button><i class="fa fa-search"></i></button>
              </div>
            </div>
          </div>
        </div>
      </section>

       
      
      <!-- 🔹 SECTION 2: FLOATING SLIDER -->
      
      <div class="routes-heading">
        <h2>Routes</h2>
        <p>
          Experience luxury on wheels with our premium fleet of buses designed for your comfort and convenience.
        </p>
      </div>
      <section class="main-container">
        <div class="ticket-card">
          <div class="ticket-header">
          <img src="${pageContext.request.contextPath}/assets/bus4.png" alt="LuxuryBus">

            <div class="rating"><i class="fa-solid fa-star"></i> 4.9</div>
            <h3>Luxury Linear</h3>
          </div>

          <div class="ticket-info">
            <div class="duration">
              <i class="fa-solid fa-clock"></i>
              4h 30m
            </div>
            <div class="price">Rs 2900.00</div>
          </div>

          <div class="times">
            <div>
              <p>Departure</p>
              <h4>09:15 AM</h4>
            </div>
            <div class="timeline"></div>
            <div>
              <p>Arrival</p>
              <h4>01:45 PM</h4>
            </div>
          </div>

          <div class="features">
            <span><i class="fa-solid fa-wifi"></i> WiFi</span>
            <span><i class="fa-solid fa-mug-saucer"></i> Coffee</span>
            <span><i class="fa-solid fa-plug"></i> Charging</span>
            <span>Reclining Seats</span>
            <span>Snacks</span>
          </div>
      <form action="book-seat" method="get">
      <input type="hidden" name="busId" value="1" />
      
          <button class="book-now">Book Now</button>
          </form>
        </div>
          <!-- Central glass panel -->
      <div class="dashboard-section">
        <iframe id="map-frame" src="defaultMap.html" frameborder="0">

        </iframe>
        <!-- Map -->
        <div class="map-container">
          
      
          <!-- Stats Row -->
          <div class="stats-row">
            <div class="stat-box maroon">
              <h2>10400</h2>
              <p>Lucky Passengers</p>
            </div>
            <div class="stat-box blue">
              <h2>240</h2>
              <p>Operators</p>
            </div>
            <div class="stat-box green">
              <h2>200+</h2>
              <p>Cities</p>
            </div>
          </div>
        </div>
      </div>
      

    </div>
      </section>
      
      <!-- 🔹 SECTION 3: BUS DISPLAY -->
       <section class="hero-bus" id="bus-section">
        <div id="bus-section">
          <h1 class="background-text">EXPRESS</h1>

          <div class="bus-image-wrapper">
            <img id="bus-image" src="${pageContext.request.contextPath}/assets/bus4.png" alt="Bus Image" />
          </div>

          <div class="bus-color-picker">
            <span>SHIFT COLOR</span>
            <div class="color-circles">
              <div
                class="circle"
                style="background: #d2665a"
                onclick="changeBus(0)"
              ></div>
              <div
                class="circle"
                style="background: #f7a072"
                onclick="changeBus(1)"
              ></div>
              <div
                class="circle"
                style="background: #a7d7c5"
                onclick="changeBus(2)"
              ></div>
              <div
                class="circle"
                style="background: #f4c2c2"
                onclick="changeBus(3)"
              ></div>
            </div>
          </div>

          <div class="bus-left-info">
            <p class="step" id="step">01 / 04</p>
            <h1 id="bus-name">Super Line Express</h1>
          </div>

          <div class="bus-right-info">
            <p id="bus-description">
              Colombo to Kandy - Luxury A/C bus service for a smooth ride.
            </p>
          </div>
        </div>
      </section>

      

     
      <!-- 🔹 SECTION 4: MAP VIDEO -->
      <section class="hero-map-wrapper">
        <!-- 🔸 Background Masked Video -->
        <div class="map-mask-wrapper">
          <video autoplay muted loop playsinline class="map-video">
            <source src="${pageContext.request.contextPath}/assets/map.mp4" type="video/mp4" />
          </video>
        </div>

        <!-- 🔸 Overlay Content -->
        <h2 class="section-title">Why Choose BusExpress?</h2>
        <p class="section-description">
          Experience Sri Lanka like never before. Discover hidden gems across
          the island with the most convenient, affordable, and culturally
          immersive bus rides.
        </p>
        <div class="rocket-wrapper">
          <img src="${pageContext.request.contextPath}/assets/startup.png" class="rocket" alt="Rocket" />
        </div>

        <div class="feature-cards">
          <div class="card">
            <h3>Island-Wide Coverage</h3>
            <p>
              From Jaffna to Galle, we've got you covered across 7,500+ cities
              and villages.
            </p>
          </div>
          <div class="card">
            <h3>Comfort & Safety</h3>
            <p>
              Modern A/C coaches, verified drivers, and live tracking for your
              peace of mind.
            </p>
          </div>
          <div class="card">
            <h3>Instant Booking</h3>
            <p>Book in seconds. Get digital tickets directly to your device.</p>
          </div>
        </div>
        <form action="book-seat" method="get">
         <input type="hidden" name="busId" value="1" />
        <button  class="cta-button">Start Your Journey</button>
        
     </form>
      </section>
    </div>

      <jsp:include page="/footer.jsp" /> 
     

    <!-- Scripts remain the same -->

   <script src="js/customer/script.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gsap@3.13.0/dist/gsap.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/gsap@3.13.0/dist/SplitText.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/ScrollTrigger.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/MotionPathPlugin.min.js"></script> 
    <script>
      gsap.registerPlugin(ScrollTrigger, SplitText, MotionPathPlugin);

      gsap.to(".rocket", {
        duration: 8,
        ease: "power1.inOut",
        repeat: -1, // 🔁 Loop forever
        yoyo: true, // ↔️ Rocket goes back and forth
        motionPath: {
          path: [
            { x: 100, y: -30 },
            { x: 200, y: 30 },
            { x: 300, y: -50 },
            { x: 400, y: 40 },
            { x: 600, y: -70 },
            { x: -600, y: -70 },
            { x: -400, y: 40 },
            { x: -300, y: -50 },
            { x: -200, y: 30 },
            { x: -100, y: -30 },
          ],
          curviness: 1.25,
          autoRotate: true,
        },
        scrollTrigger: {
          trigger: ".rocket-section",
          start: "top 80%",
          end: "bottom top",
          toggleActions: "restart pause resume pause",
          scrub: false,
        },
      });
/* 
      new Swiper(".mySwiper", {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        loop: true,
        autoplay: {
          delay: 3000,
          disableOnInteraction: false,
        },
        spaceBetween: 20,
        coverflowEffect: {
          rotate: 0,
          stretch: 0,
          depth: 200,
          modifier: 2,
          slideShadows: false,
        },
      });

      // Split text animation
      SplitText.create(".split", {
        type: "words, chars",
        onSplit(self) {
          gsap.from(self.chars, {
            duration: 1,
            y: 100,
            autoAlpha: 0,
            stagger: 0.05,
          });
        },
      });

      // ScrollTrigger animations for Section 4
      gsap
        .timeline({
          scrollTrigger: {
            trigger: ".hero-map-wrapper",
            start: "top 80%",
            end: "bottom 90%",
            scrub: true,
          },
        })
        .from(".map-video", {
          scale: 0.9,
          opacity: 0,
          duration: 1.5,
          ease: "power3.out",
        })
        .from(
          ".section-title",
          {
            y: 100,
            opacity: 0,
            duration: 1,
          },
          "-=1"
        )
        .from(
          ".section-description",
          {
            y: 50,
            opacity: 0,
            duration: 1,
          },
          "-=0.8"
        )
        .from(
          ".card",
          {
            y: 60,
            opacity: 0,
            stagger: 0.3,
            duration: 1,
          },
          "-=0.5"
        )
        .from(
          ".cta-button",
          {
            y: 40,
            opacity: 0,
            duration: 3,
          },
          "-=0.5"
        );

      gsap.registerPlugin(ScrollTrigger);
      gsap
        .timeline({
          scrollTrigger: {
            trigger: "#bus-section",
            start: "top 70%", 
            end: "bottom 20%", 
            scrub: 1, 
            toggleActions: "play none none reset", 
            markers: false,
          },
        })
        .from(".background-text", {
          opacity: 0,
          scale: 0.8,
          duration: 1,
          ease: "power2.out",
        })
        .from(
          "#bus-image",
          {
            opacity: 0,
            y: 100,
            duration: 1,
            ease: "power2.out",
          },
          "-=0.8"
        )
        .from(
          ".bus-left-info",
          {
            opacity: 0,
            x: -50,
            duration: 2,
            ease: "power2.out",
          },
          "-=0.6"
        )
        .from(
          ".bus-right-info",
          {
            opacity: 0,
            x: 50,
            duration: 2,
            ease: "power2.out",
          },
          "-=1"
        ); */
    </script>
  </body>
</html>

<!-- <div class="floating-slider">
          <h2 class="product-title">Our Offers</h2>
          <div class="swiper mySwiper">
            <div class="swiper-wrapper">
              <div class="swiper-slide">
                <img src="./assets/r2.png" alt="Offer" />
                <h3>Curry</h3>
                <p>$12</p>
                <button>Add to Cart</button>
              </div>
              <div class="swiper-slide">
                <img src="./assets/g2.png" alt="Offer" />
                <h3>Pizza</h3>
                <p>$10</p>
                <button>Add to Cart</button>
              </div>
              <div class="swiper-slide">
                <img src="./assets/w1.png" alt="Offer" />
                <h3>Pizza</h3>
                <p>$10</p>
                <button>Add to Cart</button>
              </div>
              <div class="swiper-slide">
                <img src="./assets/darkblue.png" alt="Offer" />
                <h3>Pizza</h3>
                <p>$10</p>
                <button>Add to Cart</button>
              </div>
            </div>
          </div>
        </div> -->
