<html>
	<head>
		<title>PHP Stuff</title>
	</head>
	<body>
	<p>
This is my first PHP program.
	</p>
	<p>
Doesn't it look cool?
		<a href="second.php?name=Karl&status=learning">Click Me!</a>
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
	echo "${_GET['name']} is currently ${_GET['status']}.";
?>
	</p>
	</body>
</html>
