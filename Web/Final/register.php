<?php
	$page_title = 'User Management';
	require_once('header.php');
	
	$adminID = $_GET['adminID'];
	
	//Check to see if there's a numeric adminID in the querystring
	if (!empty($adminID) && is_numeric($adminID)) {
		
		require('db.php');
		
		//Get the username of that user
		$sql = "SELECT username FROM admins WHERE adminID = :adminID";
		$cmd = $conn->prepare($sql);
		$cmd->bindParam(':adminID', $adminID, PDO::PARAM_INT);
		$cmd->execute();
		$username = $cmd->fetchColumn();

		$conn = null;
	} //End of if
?>

<h1 class="text-center"><?php echo $page_title ?></h1>
<!-- Form for registering -->
<form class="form-horizontal" method="post" action="save-registration.php">
<!-- Group containing username label and field -->
<div class="form-group">
    <label for="username" class="col-sm-2 control-label">Username</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="username" name="username" required placeholder="Enter Email" value="<?php echo $username ?>"/>
    </div>
</div>
<!-- Group containing password label and field -->
<div class="form-group">
	<label for="password" class="col-sm-2 control-label">Password</label>
	<div class="col-sm-10">
		<input type="password" class="form-control" id="password" name="password" required placeholder="Enter Password"/>
	</div>
</div>
<!-- Group containing password confirmation label and field -->
<div class="form-group">
	<label for="confirm" class="col-sm-2 control-label">Confirm Password</label>
	<div class="col-sm-10">
		<input type="password" class="form-control" id="confirm" name="confirm" required placeholder="Re-enter Password"/>
	</div>
</div>
<!-- Area for buttons -->
<div align="center">
	<!-- If an adminID was passed, change the name of the button
		and create a hidden field to store the $adminID for post-->
	<?php if ((empty($adminID))) {?>
	<input type="submit" value="Register" class="btn btn-primary btn-md" />
	<?php } else { ?>
	<input type="submit" value="Edit User" class="btn btn-primary btn-md" />
	<input type="hidden" value="<?php echo $adminID ?>" id="adminID" name="adminID"/>
	<?php } ?>
	<input type="reset" value="Clear Form" class="btn btn-primary btn-md" />
</div>
</form>

<?php require_once('footer.php'); ?>