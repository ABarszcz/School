<?PHP ob_start() ?>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Meta tag and title tag -->
    <meta content="text/html; charset=utf-8" http-equiv="content-type">
    <title>Content Creator | <?php echo $page_title; ?></title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
    <!-- Font Awesome CSS -->
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
</head>

<body>
<!-- Create the navbar -->
<nav class="navbar navbar-inverse">
<!-- TODO add customizable logo to the navbar -->
    <a class="navbar-brand" href="default.php"><img src="logo.jpg" alt="logo" height="50em" width="50em" /></a>
	
	<!-- Add the user-created pages to the navbar -->
	<?php 
	require('db.php');
	
	//Get all the page titles from the pages table and store it
	$sql = "SELECT pageID, pageTitle FROM pages";
    $cmd = $conn->prepare($sql);
    $cmd->execute();
	$pages = $cmd->fetchAll();
	$count = $cmd->rowCount();
	
	$conn=null;
	
	//Store the values
	foreach($pages as $page) {
		$pageID = $page['pageID'];
		$pageTitle = $page['pageTitle'];
		?>
		<a href="default.php?pageID=<?php echo $pageID .'" class="navbar-brand">' . $pageTitle; ?></a>
	<?php } ?>
	
	<!-- Right-aligned navbar for register/login/logout -->
    <ul class="nav navbar-nav navbar-right">
       <?php
		//Check to see if the user is logged in
		session_start();
        if (empty($_SESSION['adminID'])) { ?>
			<!-- If they aren't, display the register and log-in prompts -->
			<li><a href="register.php" title="Register">Register</a></li>
            <li><a href="login.php" title="Login">Log In</a></li>
		<?php } else { ?>
			<!-- If they are, display the log out prompt & creage page prompt -->
			<li><a href="upload-logo.php" title="Upload Logo">Upload Logo</a></li>
			<li><a href="create-page.php" title="Create Page">Create Page</a></li>
			<li><a href="pages.php" title="Page List">Page List</a></li>
			<li><a href="users.php" title="User List">User List</a></li>
			<li><a href="logout.php" title="Logout">Log Out</a></li>
		<?php } ?>
    </ul>
</nav>
<?php ob_flush(); ?>
<main class="container">