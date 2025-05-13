<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList" %>
<%
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");

    if (name == null || email == null || phone == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<String> bookedSeats = (List<String>) request.getAttribute("bookedSeats");
    if (bookedSeats == null) {
        bookedSeats = new ArrayList<>();
    }
%>

 <%
    String routeName = (String) request.getAttribute("routeName");
    String busName = (String) request.getAttribute("busName");
    Double pricePerSeat = null;
    Object priceObj = request.getAttribute("pricePerSeat");
    if (priceObj instanceof Double) {
        pricePerSeat = (Double) priceObj;
    } else if (priceObj != null) {
        pricePerSeat = Double.parseDouble(priceObj.toString());
    }
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Bus Seat Selection</title>
    <link rel="stylesheet" href="styles/customer/seatsbooking.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Margarine&family=Nunito&family=Orbitron&family=Poppins:wght@400;700&display=swap" rel="stylesheet" />
      <script src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs" type="module"></script>
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet" />
    		<link href="https://fonts.googleapis.com/css?family=Courgette|Open+Sans&display=swap" rel="stylesheet"> 
  </head>


  
  
  
	

    <body>
        
    

  
  
  
  <jsp:include page="/Navbar.jsp"/>
  
  
    <!-- === HEADER SECTION === -->
   <div class="bokeh">
    <div class="svg-header">
      <div class="ag-maecenas-block">
        <div class="ag-maecenas_title">
          <marquee direction="left">
            <div class="ag-format-container">
              <h4 class="glow ag-format-container">EXPRESS</h4>
            </div>
          </marquee>
        </div>
        <div class="ag-format-container">
          <section class="ag-maecenas_box">Hop On! Select Your Seat Below</section>
        </div>
      </div>
    </div>
    
     <div class="bookcuss">

    <!-- === MAIN SECTION: Rocket + Seat Layout + Form + Card === -->
    <div class="orbit-wrapper">
      <!-- === BUS SEAT LAYOUT === -->
      <div class="bus-layout" id="seatLayout">
        <h1>Choose Your Seat</h1>
        <!-- Seats injected by JS -->
      </div>

      <!-- === SVG ORBIT PATH === -->
      <div class="con">
        <svg viewBox="0 0 600 600" class="orbit-path">
          <circle id="orbitPath" cx="300" cy="300" r="200" fill="none" stroke="transparent" />
        </svg>
      </div>

      <!-- === ROCKET === -->
      <img src="${pageContext.request.contextPath}/assets/busrock.png" class="rocket" alt="Rocket" />

      <!-- === FORM + TICKET CARD === -->
      <div class="ticket-wrapper">
        <form action="book-seat" method="post" class="container">
          <div class="brand-logo">
            <img src="${pageContext.request.contextPath}/assets/logo.png" alt="Logo" />
          </div>
          <div class="brand-title">EXPRESS</div>
          <div class="inputs">
            <label>Name</label>
            <input type="text" name="name" value="<%= name %>" readonly />

            <label>Phone no:</label>
            <input type="text" name="phone" value="<%= phone %>" readonly />

            <label>Email</label>
            <input type="email" name="email" value="<%= email %>" readonly />
     <div class="seat-type-row">
  <label><input type="radio" name="seatType" value="Adult" checked /> Adult</label>
  <label><input type="radio" name="seatType" value="Child" /> Child</label>
</div>

<!-- ✅ New: Number of Seats -->
<!-- <div class="seat-count"> -->
  <label for="seatCount">How many seats?</label>
 <input type="text" name="seatCount" id="seatCountInput" />
<!-- </div> -->
           

            <p>Selected Seats: <span id="selectedSeatsText">None</span></p>
            <p>Total Price: <span id="totalPrice">0 LKR</span></p>
            
            <input type="hidden" name="customerId" value="<%= session.getAttribute("customer_id") %>" />
            <input type="hidden" name="busId" value="<%= request.getAttribute("busId") %>" />
           <input type="date" name="journeyDate" value="<%= request.getAttribute("journeyDate") %>" />

  <!-- ✅ Required for booking -->
  <input type="hidden" name="seats" id="seatInput" required />
  <input type="hidden" name="totalPrice" id="priceInput" required />
<!-- Hidden inputs that JS will update -->
<input type="hidden" name="seatType" id="seatTypeInput" />
<!-- <input type="hidden" name="seatCount" id="seatCountInput" /> -->


           
            <button type="submit" class="check">Proceed to Checkout</button>
          

          </div>
        </form>

        <!-- === TICKET CARD === -->
        <div class="lottie">
        
        <div class="card">
          <div class="card__info">
            <div class="card__logo">BusyBus</div>
            <div class="card__chip">
              <div class="card__chip-texture"></div>
            </div>
            <div class="card__type">Bus Ticket</div>
            <div class="card__number">
             <span class="card__digit-group">Passenger: <%= name %></span>
             <span class="card__digit-group">Route: <%=routeName %></span>
             <span class="card__digit-group">Operator: <%= busName %></span>
            <span class="card__digit-group" id="cardSeats">Seats: </span><br>
            <span class="card__digit-group" id="cardPrice">Price: -</span>

            </div>
          </div>
          <div class="card__texture"></div>
         
        
</div>

 <div class=lot>
          <dotlottie-player
  src="https://lottie.host/ef95708f-2dd1-48cf-9739-0cfe047bb3d4/mMOIACBjdD.lottie"
  background="transparent"
  speed="1"
  style="width: 300px; height: 300px"
  loop
  autoplay
></dotlottie-player>
</div>
        </div>
        
      </div>
    </div>
    
    </div>
    </div>
 <jsp:include page="/footer.jsp" /> 
    <!-- === Inject bookedSeats from JSP to JS === -->
    <script>
      const bookedSeats = [
        <% for (int i = 0; i < bookedSeats.size(); i++) {
             out.print("\"" + bookedSeats.get(i).split("-")[0].trim() + "\"");
             if (i < bookedSeats.size() - 1) out.print(", ");

           }
        %>
      ];
      console.log("Booked:", bookedSeats);
      window.pricePerSeat = <%= pricePerSeat != null ? pricePerSeat : 1000 %>;
      window.totalSeats = <%= request.getAttribute("totalSeats") != null ? request.getAttribute("totalSeats") : 50 %>;
    </script>
   

    <!-- === JS Libraries === -->
    <script src="js/customer/booking.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gsap@3.13.0/dist/gsap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gsap@3.13.0/dist/SplitText.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gsap@3.13.0/dist/ScrollTrigger.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gsap@3.13.0/dist/MotionPathPlugin.min.js"></script>

    <!-- === Rocket Animation === -->
    <script>
      gsap.registerPlugin(ScrollTrigger, SplitText, MotionPathPlugin);
      gsap.to(".rocket", {
        duration: 8,
        ease: "power1.inOut",
        repeat: -1,
        yoyo: true,
        motionPath: {
          path: [{ x: 100, y: -30 }, { x: 200, y: 30 }, { x: 300, y: -50 }, { x: 400, y: 40 }],
          curviness: 1.5,
          autoRotate: true
        },
        scrollTrigger: {
          trigger: ".rocket-section",
          start: "top 80%",
          end: "bottom top",
          toggleActions: "restart pause resume pause",
          scrub: false
        }
      });
    </script>
  </body>
</html>
