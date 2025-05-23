<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Admin-dashboard</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/styles/Admin/admin-bus.css" />
  </head>
  <body>
 
    <nav class="admin-sidebar collapsed">
      <div class="logo-section">
        <span class="logo">🚌 BusyBus</span>
        <button id="toggle-menu-btn" class="menu-icon">☰</button>
      </div>
      <ul class="nav-links">
        <li>
          <a href="${pageContext.request.contextPath}/admin">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 -960 960 960"
              width="24px"
              fill="#1f1f1f"
            >
              <path
                d="M520-600v-240h320v240H520ZM120-440v-400h320v400H120Zm400 320v-400h320v400H520Zm-400 0v-240h320v240H120Zm80-400h160v-240H200v240Zm400 320h160v-240H600v240Zm0-480h160v-80H600v80ZM200-200h160v-80H200v80Zm160-320Zm240-160Zm0 240ZM360-280Z"
              />
            </svg>
            <span>Dashboard</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin-cus">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 -960 960 960"
              width="24px"
              fill="#1f1f1f"
            >
              <path
                d="M40-160v-112q0-34 17.5-62.5T104-378q62-31 126-46.5T360-440q66 0 130 15.5T616-378q29 15 46.5 43.5T680-272v112H40Zm720 0v-120q0-44-24.5-84.5T666-434q51 6 96 20.5t84 35.5q36 20 55 44.5t19 53.5v120H760ZM360-480q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47Zm400-160q0 66-47 113t-113 47q-11 0-28-2.5t-28-5.5q27-32 41.5-71t14.5-81q0-42-14.5-81T544-792q14-5 28-6.5t28-1.5q66 0 113 47t47 113ZM120-240h480v-32q0-11-5.5-20T580-306q-54-27-109-40.5T360-360q-56 0-111 13.5T140-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T440-640q0-33-23.5-56.5T360-720q-33 0-56.5 23.5T280-640q0 33 23.5 56.5T360-560Zm0 320Zm0-400Z"
              />
            </svg>
            <span>Customers</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin-payments">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 -960 960 960"
              width="24px"
              fill="#1f1f1f"
            >
              <path
                d="M560-440q-50 0-85-35t-35-85q0-50 35-85t85-35q50 0 85 35t35 85q0 50-35 85t-85 35ZM280-320q-33 0-56.5-23.5T200-400v-320q0-33 23.5-56.5T280-800h560q33 0 56.5 23.5T920-720v320q0 33-23.5 56.5T840-320H280Zm80-80h400q0-33 23.5-56.5T840-480v-160q-33 0-56.5-23.5T760-720H360q0 33-23.5 56.5T280-640v160q33 0 56.5 23.5T360-400Zm440 240H120q-33 0-56.5-23.5T40-240v-440h80v440h680v80ZM280-400v-320 320Z"
              />
            </svg>
            <span>Payments</span>
          </a>
        </li>

        <li class="active">
          <a href="${pageContext.request.contextPath}/admin-bus">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 -960 960 960"
              width="24px"
              fill="#1f1f1f"
            >
              <path
                d="M240-120q-17 0-28.5-11.5T200-160v-82q-18-20-29-44.5T160-340v-380q0-83 77-121.5T480-880q172 0 246 37t74 123v380q0 29-11 53.5T760-242v82q0 17-11.5 28.5T720-120h-40q-17 0-28.5-11.5T640-160v-40H320v40q0 17-11.5 28.5T280-120h-40Zm242-640h224-448 224Zm158 280H240h480-80Zm-400-80h480v-120H240v120Zm100 240q25 0 42.5-17.5T400-380q0-25-17.5-42.5T340-440q-25 0-42.5 17.5T280-380q0 25 17.5 42.5T340-320Zm280 0q25 0 42.5-17.5T680-380q0-25-17.5-42.5T620-440q-25 0-42.5 17.5T560-380q0 25 17.5 42.5T620-320ZM258-760h448q-15-17-64.5-28.5T482-800q-107 0-156.5 12.5T258-760Zm62 480h320q33 0 56.5-23.5T720-360v-120H240v120q0 33 23.5 56.5T320-280Z"
              />
            </svg>
            <span>Bus Operators</span>
          </a>
        </li>
        <li class="dropdown">
          <button class="dropdown-btn">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 -960 960 960"
              width="24px"
              fill="#1f1f1f"
            >
              <path
                d="M480-301q99-80 149.5-154T680-594q0-90-56-148t-144-58q-88 0-144 58t-56 148q0 65 50.5 139T480-301Zm0 101Q339-304 269.5-402T200-594q0-125 78-205.5T480-880q124 0 202 80.5T760-594q0 94-69.5 192T480-200Zm0-320q33 0 56.5-23.5T560-600q0-33-23.5-56.5T480-680q-33 0-56.5 23.5T400-600q0 33 23.5 56.5T480-520ZM200-80v-80h560v80H200Zm280-520Z"
              />
            </svg>
            <span class="btn-text">Manage Shedules</span>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 -960 960 960"
              width="24px"
              fill="#1f1f1f"
            >
              <path d="M480-344 240-584l56-56 184 184 184-184 56 56-240 240Z" />
            </svg>
          </button>
          <ul class="sub-menu">
            <li>
              <a href="${pageContext.request.contextPath}/admin-view-routes">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="24px"
                  viewBox="0 -960 960 960"
                  width="24px"
                  fill="#1f1f1f"
                >
                  <path
                    d="m600-120-240-84-186 72q-20 8-37-4.5T120-170v-560q0-13 7.5-23t20.5-15l212-72 240 84 186-72q20-8 37 4.5t17 33.5v560q0 13-7.5 23T812-192l-212 72Zm-40-98v-468l-160-56v468l160 56Zm80 0 120-40v-474l-120 46v468Zm-440-10 120-46v-468l-120 40v474Zm440-458v468-468Zm-320-56v468-468Z"
                  />
                </svg>
                <span>Add Routes</span>
              </a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/admin-booking">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="24px"
                  viewBox="0 -960 960 960"
                  width="24px"
                  fill="#1f1f1f"
                >
                  <path
                    d="M600-160H320q-33 0-56.5-23.5T240-240v-440h80v440h280v80ZM460-720q-33 0-56.5-23.5T380-800q0-33 23.5-56.5T460-880q33 0 56.5 23.5T540-800q0 33-23.5 56.5T460-720ZM640-80v-200H440q-33 0-56.5-23.5T360-360v-220q0-42 29-71t71-29q42 0 71 29t29 71v180h80q33 0 56.5 23.5T720-320v240h-80Z"
                  />
                </svg>
                <span>View Seat Bookings</span></a
              >
            </li>
             <li>
              <a href="${pageContext.request.contextPath}/admin-add-ticket">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="24px"
                  viewBox="0 -960 960 960"
                  width="24px"
                  fill="#1f1f1f"
                >
                  <path
                    d="M360-320q-17 0-28.5-11.5T320-360v-80q17 0 28.5-11.5T360-480q0-17-11.5-28.5T320-520v-80q0-17 11.5-28.5T360-640h240q17 0 28.5 11.5T640-600v80q-17 0-28.5 11.5T600-480q0 17 11.5 28.5T640-440v80q0 17-11.5 28.5T600-320H360Zm120-60q8 0 14-6t6-14q0-8-6-14t-14-6q-8 0-14 6t-6 14q0 8 6 14t14 6Zm0-80q8 0 14-6t6-14q0-8-6-14t-14-6q-8 0-14 6t-6 14q0 8 6 14t14 6Zm0-80q8 0 14-6t6-14q0-8-6-14t-14-6q-8 0-14 6t-6 14q0 8 6 14t14 6ZM280-40q-33 0-56.5-23.5T200-120v-720q0-33 23.5-56.5T280-920h400q33 0 56.5 23.5T760-840v720q0 33-23.5 56.5T680-40H280Zm0-120v40h400v-40H280Zm0-80h400v-480H280v480Zm0-560h400v-40H280v40Zm0 0v-40 40Zm0 640v40-40Z"
                  />
                </svg>
                <span>Add Tickets</span></a>
            </li>
          </ul>
        </li>
          <div class="admin-bottom-section">
        <li class="admin-user">
        <svg xmlns="http://www.w3.org/2000/svg" 
        height="24px" viewBox="0 -960 960 960" 
        width="24px" fill="#1f1f1f">
        <path d="M234-276q51-39 114-61.5T480-360q69 0 132 22.5T726-276q35-41 54.5-93T800-480q0-133-93.5-226.5T480-800q-133 0-226.5 93.5T160-480q0 59 19.5 111t54.5 93Zm246-164q-59 0-99.5-40.5T340-580q0-59 40.5-99.5T480-720q59 0 99.5 40.5T620-580q0 59-40.5 99.5T480-440Zm0 360q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q53 0 100-15.5t86-44.5q-39-29-86-44.5T480-280q-53 0-100 15.5T294-220q39 29 86 44.5T480-160Zm0-360q26 0 43-17t17-43q0-26-17-43t-43-17q-26 0-43 17t-17 43q0 26 17 43t43 17Zm0-60Zm0 360Z"/>
        </svg>
        <span>Admin Name</span>
        </li>
        <li class="admin-logout">
        <svg xmlns="http://www.w3.org/2000/svg" 
        height="24px" viewBox="0 -960 960 960"
         width="24px" fill="#1f1f1f">
         <path d="M200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h280v80H200v560h280v80H200Zm440-160-55-58 102-102H360v-80h327L585-622l55-58 200 200-200 200Z"/>
         </svg>
        <a href="BopLogoutServlet">
         <span>Logout</span>
         </a>
        </li>
        </div>
      </ul>
    </nav>
    <!-- ///////////main///////////////////// -->
    <main id="busOp-view">
      <div class="busOp-header">
        <h1>View Bus Operators</h1>
        <div class="search-bar">
          <input type="text" onkeyup="searchBusOperator()" placeholder="Search bus Operator..." id="search-bar"/>
          <button class="search-btn">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="25px"
              viewBox="0 -960 960 960"
              width="26px"
              fill="white"
            >
              <path
                d="M784-120 532-372q-30 24-69 38t-83 14q-109 0-184.5-75.5T120-580q0-109 75.5-184.5T380-840q109 0 184.5 75.5T640-580q0 44-14 83t-38 69l252 252-56 56ZM380-400q75 0 127.5-52.5T560-580q0-75-52.5-127.5T380-760q-75 0-127.5 52.5T200-580q0 75 52.5 127.5T380-400Z"
              />
            </svg>
          </button>
        </div>
      </div>
      <div class="recent-reservations table-bus" >
        <h2 class="recent-tickets">Bus Operators History</h2>
        <table class="main-table" id="bus-table">
          <thead>
            <tr>
              <th>Bus Name</th>
   			  <th>Bus Type</th>
    		  <th>Price</th>
    		  <th>Departure</th>
              <th>Arrival</th>
              <th>Seats Available</th>
              <th>Status</th>
              <th>Action</th>
             
            </tr>
          </thead>
          <tbody>
           <c:forEach var="bus" items="${busList}">
            <tr>
                <td>${bus.bus_name}</td>
                <td>${bus.bus_type}</td>
                <td>${bus.price_per_seat}</td>
                <td>${bus.departure_time}</td>
                <td>${bus.arrival_time}</td>
                <td>${bus.seats_available}</td>
                <td> 
                <c:choose>
    		   <c:when test="${bus.is_active}">
               Active
             </c:when>
             <c:otherwise>
             Inactive
             </c:otherwise>
           </c:choose></td>
              <td>
                <a href="${pageContext.request.contextPath}/admin-delete-bus?id=${bus.bus_id}" class="dlt-a-tag" onclick="return confirm('Are you sure?')">
                 <button class="dlt-btn">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    height="24px"
                    viewBox="0 -960 960 960"
                    width="24px"
                    fill="#1f1f1f"
                  >
                    <path
                      d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"
                    />
                  </svg>
                </button>

                </a>
              </td>
            </tr>
             </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
        <script src="${pageContext.request.contextPath}/js/Admin/admin-search.js"></script>
  </body>
  </html>
  