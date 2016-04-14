<?php
$page_title = 'Log In';
require_once('header.php');
?>

<h1 class="text-center">Log In</h1>
<!-- Start of log in form -->
<form class="form-horizontal" method="post" action="validate.php">
<!-- Group containing username label and field -->
<div class="form-group">
    <label for="username" class="col-sm-2 control-label">Username</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="username" name="username" required placeholder="Enter Email"/>
    </div>
</div>
<!-- Group containing password label and field -->
<div class="form-group">
	<label for="password" class="col-sm-2 control-label">Password</label>
	<div class="col-sm-10">
		<input type="password" class="form-control" id="password" name="password" required placeholder="Enter Password"/>
	</div>
</div>
<!-- Area for buttons -->
    <div align="center">
		<input type="submit" value="Log In" class="btn btn-primary btn-md" />
		<input type="reset" value="Clear Form" class="btn btn-primary btn-md" />
	</div>
</form>

<?php require_once('footer.php'); ?>
