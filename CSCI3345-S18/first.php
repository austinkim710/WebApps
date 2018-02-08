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
	</p>
<?php
	print_r($_POST);
	print_r($_GET);
	$a1 = array(1,2,3,4);
	$a2 = array("one" => 1, "two" =>2);
	echo "123e2abc"+5;
	echo "<br>";
	echo "123abc"=="123";
	echo "<br>";
	for($i=0; $i<10; $i++) {
		echo '<p>'.$i.'</p>';
	}
	echo "<br>";
	$varname = "a1";
	echo $a1[2];
	echo $a2["one"];
	echo "<p>";
	print_r($$varname);
	echo "<p>";
	var_dump($a2);

	function foo() {
		echo "<p>foo<p>";
	}
	function bar() {
		echo "<p>bar<p>";
	}
	$f = "bar";
	$f();

	$str = <<< ml
This is a long string.
$a1[1]
ml;
	echo $str;
?>
	</body>
</html>
