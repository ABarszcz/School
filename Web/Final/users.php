<?php ob_start(); ?>

<!-- JavaScript SDK for Facebook API -->
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<?php

//Authentication check
require_once ('auth.php');

//Set page title and embed header
$page_title = 'Administrators';
require_once('header.php');

echo'<h1 class="text-center">Administrators</h1>';

//Error handler
try {
	require('db.php');

	//Prepare sql
	$sql = "SELECT * FROM admins ORDER BY username";
	
	//Execute sql
	$cmd = $conn->prepare($sql);
	$cmd->execute();
	
	//Acquire all information from sql statement
	$users = $cmd->fetchAll();

	//Create table framework
	echo '<table class="table table-condensed"><thead>
	<th>Username</th>
	<th>Edit</th>
	<th>Delete</th>
	</thead><tbody>';

	//Loop through the data, creating a new table row for each user and putting each value in a new column
	foreach ($users as $user) {
		echo '<tr><td>' . $user['username'] . '</td>
		<td><a href="register.php?adminID=' . $user['adminID'] . '">Edit</a></td>
		<td><a href="delete-user.php?adminID=' . $user['adminID'] .
                '" onclick="return confirm(\'Are you sure?\');">
                Delete</a></td></tr>';
	} //end of loops
	//Close table
	echo '</tbody></table>';
	
?>
	<!-- Display the Facebook API -->
	<div class="fb-comments" data-href="http://gc200318107.computerstudi.es/project/users.php" data-width="500" data-numposts="5"></div>
<?php
	
} catch (Exception $e) {
    //Email error to myself
    require_once('mail.php');
	
	echo($e);

    //Redirect user to the error page
    header('location:error.php');
} //End of catch

$conn = null;
require_once('footer.php');
ob_flush();
?>
