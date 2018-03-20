<?php
$mysqli = new mysqli("dias11.cs.trinity.edu", "akim", "0789066", "akim");
if($mysqli->connect_errno) {
	echo "Failed to connect to database: " . $mysqli->connect_error;
}
?>
