<?php
    session_start();
    $_SESSION['loggedIn'] = false;
    if(session_destroy()) {
    header("Location: loginscreen.html");
  }
?>
