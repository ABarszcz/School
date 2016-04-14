<?php  ob_start();

//Authentication check
require_once ('auth.php');

//Set page title and embed header
$page_title = 'Page List';
require_once('header.php');

echo'<h1 class="text-center">Page List</h1>';

//Error handler
try {
	require('db.php');

	//Prepare sql
	$sql = "SELECT * FROM pages ORDER BY pageID";
	
	//Execute sql
	$cmd = $conn->prepare($sql);
	$cmd->execute();
	
	//Acquire all information from sql statement
	$pages = $cmd->fetchAll();

	//Create table framework
	echo '<table class="table table-condensed"><thead>
	<th>Page Title</th>
	<th>Page Content</th>
	<th>Edit</th>
	<th>Delete</th>
	</thead><tbody>';

	//Loop through the data, creating a new table row for each user and putting each value in a new column
	foreach ($pages as $page) {
		echo '<tr><td>' . $page['pageTitle'] . '</td>
		<td>' . $page['pageContent'] . '</td>
		<td><a href="create-page.php?pageID=' . $page['pageID'] . '">Edit</a></td>
		<td><a href="delete-page.php?pageID=' . $page['pageID'] .
                '" onclick="return confirm(\'Are you sure?\');">
                Delete</a></td></tr>';
	}
	//Close table
	echo '</tbody></table>';

	$conn = null;
} catch (Exception $e) {
    //Email error to myself
    require_once('mail.php');

    //Redirect user to the error page
    header('location:error.php');
} //End of catch

$conn=null;
require_once('footer.php');
ob_flush();
?>
