<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    
    <!DOCTYPE html>
<html>
  <head>
    <title>Slide Navbar</title>
    <link rel="stylesheet" type="text/css" href="slide navbar style.css" />
    <link
      href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="styles/customer/signup.css" />
  </head>
  <body>
    <br>
<br>
    <div class="cont">
     <form action="login" method="POST" >
        <div class="form sign-in">
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
         <button type="submit" class="submit">Login</button>
         
        </div>
        </form>
        <div class="sub-cont">
            <div class="img">
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
                    <input type="text" id="firstName" name="first_name" placeholder="First Name" required />

                </label>
                <label for="lastName">
                    <span>Last Name</span>
                    <input type="text" id="lastName" name="last_name" placeholder="Last Name" required />
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
               <button type="submit" class="submit">Sign Up</button>

                
                </form>
            </div>
        </div>
    </div>

    <script>
        document.querySelector('.img__btn').addEventListener('click', function() {
            document.querySelector('.cont').classList.toggle('s--signup');
        });
    </script>
  </body>
</html>
