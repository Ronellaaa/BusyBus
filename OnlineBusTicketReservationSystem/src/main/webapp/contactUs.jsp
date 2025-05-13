<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Contact Us - BusBuddy</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;700&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    />
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
    <script
      src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs"
      type="module"
    ></script>

    <style>
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
      }

      body {
        background: linear-gradient(120deg, #ff8008, #ffc837);
        color: #fff;
       /*  min-height: 100vh;  */
        overflow-x: hidden;
        animation: fadeInBody 1.5s ease;
      }

      @keyframes fadeInBody {
        from {
          opacity: 0;
        }
        to {
          opacity: 1;
        }
      }

      .contact-container {
      top:190px;
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 40px;
        padding: 50px;
        max-width: 1200px;
        margin: auto;
        animation: slideUp 1.5s ease;
      }

      @keyframes slideUp {
        from {
          transform: translateY(50px);
          opacity: 0;
        }
        to {
          transform: translateY(0);
          opacity: 1;
        }
      }

      .contact-info {
        display: flex;
        flex-direction: column;
        justify-content: center;
      }

      .contact-info h1 {
        font-size: 3rem;
        margin-bottom: 20px;
      }

      .info-box {
        margin: 15px 0;
        display: flex;
        align-items: center;
        gap: 15px;
        font-size: 1.1rem;
        transition: transform 0.3s;
      }

      .info-box:hover {
        transform: scale(1.05);
      }

      .info-box i {
        font-size: 1.5rem;
        color: #ffda44;
      }

      .socials {
        margin-top: 30px;
      }

      .socials a {
        margin-right: 15px;
        color: #fff;
        font-size: 1.5rem;
        transition: transform 0.3s, color 0.3s;
      }

      .socials a:hover {
        transform: scale(1.2);
        color: #ffda44;
      }

      .contact-form {
        background: rgba(255, 255, 255, 0.1);
        padding: 30px;
        border-radius: 20px;
        backdrop-filter: blur(8px);
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
        animation: slideInRight 1.5s ease;
      }

      @keyframes slideInRight {
        from {
          transform: translateX(50px);
          opacity: 0;
        }
        to {
          transform: translateX(0);
          opacity: 1;
        }
      }

      .contact-form h2 {
        margin-bottom: 20px;
      }

      .contact-form input,
      .contact-form textarea {
        width: 100%;
        padding: 12px;
        margin-bottom: 15px;
        border: none;
        border-radius: 10px;
        background: rgba(255, 255, 255, 0.2);
        color: #fff;
        font-size: 1rem;
        transition: 0.3s ease;
      }

      .contact-form input:focus,
      .contact-form textarea:focus {
        outline: none;
        background: rgba(255, 255, 255, 0.3);
      }

      .contact-form input::placeholder,
      .contact-form textarea::placeholder {
        color: #ddd;
      }

      .contact-form button {
        background-color: #ffda44;
        color: #333;
        padding: 12px 25px;
        border: none;
        border-radius: 10px;
        font-size: 1rem;
        cursor: pointer;
        transition: 0.3s;
      }

      .contact-form button:hover {
        background-color: #fff;
        transform: scale(1.05);
      }

      .map {
        margin-top: 50px;
        text-align: center;
        animation: fadeInBody 1.5s ease;
      }

      iframe {
        width: 100%;
        height: 300px;
        border: none;
        border-radius: 15px;
      }

      .lottie-box {
        margin-top: 30px;
        max-width: 400px;
      }

      @media (max-width: 768px) {
        .contact-container {
          grid-template-columns: 1fr;
        }

        .contact-info h1 {
          font-size: 2.5rem;
        }

        .lottie-box {
          margin: 0 auto 20px;
        }
      }
    </style>
  </head>
  <body>
  <jsp:include page="/Navbar.jsp" />
    <div class="contact-container">
      <div class="contact-info">
        <div class="lottie-box">
          <dotlottie-player
            src="https://lottie.host/b935439f-88c7-460b-a4dd-c71e2a715f12/9Wl18q8imp.lottie"
            background="transparent"
            speed="1"
            style="width: 200px; height: 200px"
            loop
            autoplay
          ></dotlottie-player>
        </div>
        <h1>Contact BusyBus</h1>
        <div class="info-box">
          <i class="fas fa-phone"></i>
          <span>+94 073 457 789</span>
        </div>
        <div class="info-box">
          <i class="fas fa-envelope"></i>
          <span>support@busybus.com</span>
        </div>
        <div class="info-box">
          <i class="fas fa-map-marker-alt"></i>
          <span>123 Main St, Nawaloka, Colombo</span>
        </div>
        <div class="socials">
          <a href="#"><i class="fab fa-facebook-f"></i></a>
          <a href="#"><i class="fab fa-instagram"></i></a>
          <a href="#"><i class="fab fa-twitter"></i></a>
        </div>
      </div>

      <div class="contact-form">
        <h2>Send Us a Message</h2>
        <form action="#">
          <input type="text" placeholder="Your Name" required />
          <input type="email" placeholder="Your Email" required />
          <textarea rows="5" placeholder="Your Message" required></textarea>
          <button type="submit">Send Message</button>
        </form>
      </div>
    </div>

    <div class="map">
      <iframe
        src="https://maps.google.com/maps?q=London&t=&z=13&ie=UTF8&iwloc=&output=embed"
      ></iframe>
    </div>
  </body>
</html>
    