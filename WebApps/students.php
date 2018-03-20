<?php
    // Start the session
    ob_start();
    session_start();

    // Check to see if actually logged in. If not, redirect to login page
    if (!isset($_SESSION['loggedIn']) || $_SESSION['loggedIn'] == false) {
        header("Location: first.php");
    }
?>

<h1>Logged In!</h1>
<form method="post" action="logout.php">
    <input type="submit" value="Logout">
</form>

<html>
	<head>
		<title>PHP Stuff</title>
	</head>
	<body>

<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
      $username = test_input($_POST["username"]);
      $password = test_input($_POST["password"]);
    }

function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>





	Welcome <?php echo $_POST["username"]; ?><br>
	<p>

	</p>
	</body>
</html>
