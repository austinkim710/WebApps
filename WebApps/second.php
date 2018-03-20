<html>
	<head>
		<title>PHP Stuff</title>
	</head>
	<body>

<?php
    //variable initializion
    $name = $email = ;
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
      $name = test_input($_POST["name"]);
      $email = test_input($_POST["email"]);
    }

function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>

	<p>
 Username is
	</p>
	<?php
	echo "${_GET['name']} is currently ${_GET['status']}.";
	?>

	<p>
		<a href="first.php?name=Karl&status=learning">Click Me!</a>
	</p>
	<form action="second.php" method="post">
		Username: <input type="text" name="username">
		<br>
		Password: <input type="password" name="password">
		<br>
		<input type="submit" value="Submit">
	</form>
<?php
	print_r($_POST);
	print_r($_GET);
?>
	<p>
<?php

?>
	</p>
	</body>
</html>
