<?php
  session_start();
  include ("connect.php");

  $curr_user = $_SESSION['current_user'];
  $queryID = mysqli_query($mysqli, "SELECT id from user WHERE username = '$curr_user'");

  $userIDfetch = mysqli_fetch_assoc($queryID);
  $userID = $_SESSION['id'];





  if(isset($_POST['submit'])) {
    $task = test_input($_POST['Task']);
    if(empty($task)) {
      $error = "You must write a task";
    } else {
      mysqli_query($mysqli, "INSERT into tasks (task, userID) VALUES ('$task', '$userID')");
      header('Location: ToDoList.php');
    }
  }

  if (isset($_GET['del_task'])) {
    $id = $_GET['del_task'];
    mysqli_query($mysqli, "DELETE from tasks WHERE id='$id'");
  }

  $tasks = mysqli_query($mysqli, "SELECT task, id from tasks where userID = '$userID'");

  function test_input($data) {
  	$data = trim($data);
  	$data = stripslashes($data);
  	$data = htmlspecialchars($data);
  	return $data;
  }

?>






<!DOCTYPE html>
<html lang=”en”>
  <head>
    <title>To Do List</title>
    <style type="text/css">
      @import url('website.css');
      @import url('database.css');
    </style>
  </head>
  <body>
    <h1> To do list application</h1>
    <form method="POST" action = "ToDoList.php">


       <input type="text" name="Task">
       <button type="submit" name="submit">Add task</button>
    </form>
    <br>

    <table id="Tasks" align="center">
      <thead>
        <tr>
          <th>Task ID</th>
          <th>Task</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <?php $i = 1;
          while ($row = mysqli_fetch_array($tasks)) {
        ?>
          <tr>
            <td><?php echo $i; ?> </td>
              <td class="task"><?php echo $row['task'];?></td>
              <td class="delete">
                <a href="ToDoList.php?del_task=<?php echo $row['id']; ?>">Delete</a>
              </td>
          </tr>
          <?php $i++; } ?>
      </tbody>

    </table>

    <a href="logout.php">Log out</a>


  </body>
</html>
