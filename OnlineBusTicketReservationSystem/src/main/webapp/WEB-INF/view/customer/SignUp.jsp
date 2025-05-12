<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    
    <!DOCTYPE html>
<html>
  <head>
 <!--   
    <link rel="stylesheet" type="text/css" href="slide navbar style.css"/> -->
    <link
      href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap"
      rel="stylesheet"
    />
    <script src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs" type="module"></script>
    <link rel="stylesheet" href="styles/customer/signup.css" />
  </head>
  <body>
  
  
  
  <jsp:include page="/Navbar.jsp"/>
  
  <div class="leaf">
     <div>  <img src="http://www.pngmart.com/files/1/Fall-Autumn-Leaves-Transparent-PNG.png" height="75px" width="75px"/></div>
      <div><img src="http://www.pngmart.com/files/1/Autumn-Fall-Leaves-Pictures-Collage-PNG.png" height="75px" width="75px"></img></div>
      <div>  <img src="http://www.pngmart.com/files/1/Autumn-Fall-Leaves-Clip-Art-PNG.png" height="75px" width="75px" ></img></div>
      <div><img  src="http://www.pngmart.com/files/1/Green-Leaves-PNG-File.png" height="75px" width="75px"></img></div>
       <div> <img src="http://www.pngmart.com/files/1/Transparent-Autumn-Leaves-Falling-PNG.png" height="75px" width="75px"></img></div>
     <div>   <img src="http://www.pngmart.com/files/1/Realistic-Autumn-Fall-Leaves-PNG.png" height="75px" width="75px"/></div>
    <div> <img src="http://www.pngmart.com/files/1/Transparent-Autumn-Leaves-Falling-PNG.png" height="75px" width="75px"></img></div>
            
    
     
     <div class="leaf leaf1">
     <div>  <img src="http://www.pngmart.com/files/1/Fall-Autumn-Leaves-Transparent-PNG.png" height="75px" width="75px"></img></div>
      <div><img src="http://www.pngmart.com/files/1/Autumn-Fall-Leaves-Pictures-Collage-PNG.png" height="75px" width="75px"></img></div>
      <div>  <img src="http://www.pngmart.com/files/1/Autumn-Fall-Leaves-Clip-Art-PNG.png" height="75px" width="75px" ></img></div>
      <div><img  src="http://www.pngmart.com/files/1/Green-Leaves-PNG-File.png" height="75px" width="75px"></img></div>
       <div> <img src="http://www.pngmart.com/files/1/Transparent-Autumn-Leaves-Falling-PNG.png" height="75px" width="75px"></img></div>
     <div>   <img src="http://www.pngmart.com/files/1/Realistic-Autumn-Fall-Leaves-PNG.png" height="75px" width="75px"/></div>
     <div> <img src="http://www.pngmart.com/files/1/Transparent-Autumn-Leaves-Falling-PNG.png" height="75px" width="75px"></img></div>
            
     </div>
     
     <div class="leaf leaf2">
     <div>  <img src="http://www.pngmart.com/files/1/Fall-Autumn-Leaves-Transparent-PNG.png" height="75px" width="75px"></img></div>
      <div><img src="http://www.pngmart.com/files/1/Autumn-Fall-Leaves-Pictures-Collage-PNG.png" height="75px" width="75px"></img></div>
      <div>  <img src="http://www.pngmart.com/files/1/Autumn-Fall-Leaves-Clip-Art-PNG.png" height="75px" width="75px" ></img></div>
      <div><img  src="http://www.pngmart.com/files/1/Green-Leaves-PNG-File.png" height="75px" width="75px"></img></div>

       <div> <img src="http://www.pngmart.com/files/1/Transparent-Autumn-Leaves-Falling-PNG.png" height="75px" width="75px"></img></div>
     <div>   <img src="http://www.pngmart.com/files/1/Realistic-Autumn-Fall-Leaves-PNG.png" height="75px" width="75px"/></div>
      <div> <img src="http://www.pngmart.com/files/1/Transparent-Autumn-Leaves-Falling-PNG.png" height="75px" width="75px"></img></div>
            
     </div>
  
  </div>
  
  
    <br>
<br>
    <div class="cont">
     <form action="login" method="POST" >
        <div class="log sign-in">
            <h2>Welcome</h2>
            <label>
                <span>Email</span>
                  <input type="email" id="email" name="email" placeholder="Email" required />
            </label>
            <label>
                <span>Password</span>
               <input type="password" id="password" name="password" placeholder="Password" required />
            </label>

            <p class="forgot-pass">Forgot password?</p>
         <button type="submit" class="submit b1">Login</button>
         
        </div>
        </form>
        <div class="sub-cont">
            <div class="img">
           
           <div class="ilu">

  <img
      src="${pageContext.request.contextPath}/assets/il.png"
      alt="User"
    />
    </div>
<!-- 
<dotlottie-player src="https://lottie.host/b935439f-88c7-460b-a4dd-c71e2a715f12/9Wl18q8imp.lottie" background="transparent" speed="1" style="width: 200px; height: 100px" loop autoplay></dotlottie-player> -->

                <div class="img__text m--up">
                 
                    <h3>Don't have an account? Please Sign up!</h3>
                </div>
                <div class="img__text m--in">
                
                    <h3>If you already has an account, just sign in.</h3>
                </div>
                <div class="img__btn">
                    <span class="m--up">Sign Up</span>
                    <span class="m--in">Sign In</span>
                </div>
            </div>
            <div class="form sign-up">
                <h2>Create your Account</h2>
                
                <form action="sign-up" method="POST" >
                <label  for="firstName">
                    <span>Name</span>
                    <input type="text" id="firstName" name="first_Name" placeholder="First Name" required />

                </label>
                <label for="lastName">
                    <span>Last Name</span>
                    <input type="text" id="lastName" name="last_Name" placeholder="Last Name" required />
                </label>
                <label  for="email">
                    <span>Email</span>
                    <input type="email" id="email" name="email" placeholder="Email" required />
                </label>
                <label for="phoneNumber">
                  <span>Phone Number</span>
                  <input type="tel" id="phoneNumber" name="phoneNumber" placeholder="Phone Number" required />
              </label>
            
            <label>
              <span>Password</span>
              <input type="password" id="password" name="password" placeholder="Password" required />
          </label>
               <button type="submit" class="submit b1">Sign Up</button>

                
                </form>
            </div>
        </div>
    </div>
<%-- <jsp:include page="/footer.jsp" />  --%>
    <script>
        document.querySelector('.img__btn').addEventListener('click', function() {
            document.querySelector('.cont').classList.toggle('s--signup');
        });
    </script>
  </body>
</html>
