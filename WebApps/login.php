<!--For this task you need to make a PHP based set of pages that implements a task tracking system/todo list
//with a login. So each user can add or remove todo items/tasks that they have to accomplish.
//When they log back in, they should get the items they had from the last time. They should be able to log out.
//You need to send me login credentials for two users so that I can verify they have different logins
//and different lists of tasks.
//You do not have to include the ability to add new accounts.
//Those can be hard coded or put in some data file/database that the site does not modify.
-->
<!DOCTYPE HTML>
<?php
	session_start();
	include("connect.php");
	$error = "";

	if ($_SERVER['SERVER_NAME'] != "dias11.cs.trinity.edu") {
    echo "<p>You must access this page from on campus through dias11.</p></body></html>";
    die ();
	}

	if ($_SERVER["REQUEST_METHOD"] == "POST") {
		$username = test_input($_POST["username"]);
		$password = test_input($_POST["password"]);
	}

	if (isset($_SESSION['loggedIn']) && $_SESSION['loggedIn'] == true) {
			$error = "Login Success";
			header('Location: ToDoList.php');
	}
	//user check
	if (isset($_POST['username'])) {
		if(empty($_POST['username']) || empty($_POST['password']) ) {
			$error = "You must fill out the fields!";
			//header('Location: login.php')
			echo ($error);
		} else {
			$username = $mysqli->real_escape_string($_POST['username']);
			$password = $mysqli->real_escape_string($_POST['password']);



			$queryLogin = mysqli_query($mysqli, "select * FROM user where username = '$username' and password = '$password'");
			if ($row = mysqli_fetch_row($queryLogin)) {
				$_SESSION["id"] = $row[0];
				$_SESSION['current_user'] = $username;

				header('Location: ToDoList.php');
			} else {
				$error = "Invalid login";

			}
		}
	}


	//variable initializion


function test_input($data) {
	$data = trim($data);
	$data = stripslashes($data);
	$data = htmlspecialchars($data);
	return $data;
}
?>




<html>
	<head>
		<title>PHP Stuff</title>
	</head>
	<body>


	<p>
 		<?php echo $error; ?>
	</p>


	<p>

	</p>
	</body>
</html>
