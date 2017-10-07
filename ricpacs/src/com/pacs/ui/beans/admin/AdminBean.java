package com.pacs.ui.beans.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import com.iac.web.util.FacesUtils;
import com.pacs.bll.admin.AdminBll;
import com.pacs.dal.dao.ApplicationUsers;
import com.pacs.dal.dao.LuModalityAlias;
import com.pacs.dal.dao.RolesApplAet;
import com.pacs.dal.dao.RolesApplModality;
import com.pacs.dal.dao.vw.LuAetVw;
import com.pacs.ui.beans.UserBean;
import com.pacs.utils.Environment;
import com.pacs.utils.MessageConstants;
import com.pacs.utils.MessageUtils;
import com.pacs.utils.NavigationConstants;


@ManagedBean(name="adminBean")
@SessionScoped
public class AdminBean 
{
	
	private ApplicationUsers toAddUser;
	private ApplicationUsers toSearchUser;
	private ApplicationUsers selectedUser;
	
	private LuModalityAlias modAliasObj;
	
	
	private List<ApplicationUsers> usersList;
	private List<RolesApplAet> selectedRolesList;
	private List<RolesApplModality> selectedModalitiesList;
	private List<LuModalityAlias> modAliasList;
	
	private ApplicationUsers currentUser;
	private String newPassword = "";
	private String newPasswordAgain = "";
	
	
	private DualListModel<String> aetRolesDual;
	private List<String> srcAetList;
	private List<String> tgtAetList;
	
	private DualListModel<String> modRolesDual;
	private List<String> srcModList;
	private List<String> tgtModList;
	
	private String scannedFileType;
	private CriteriaBean crt = (CriteriaBean)FacesUtils.getManagedBean("crit");
	
	public AdminBean() 
	{		
		// TODO Auto-generated constructor stub
		toAddUser = new ApplicationUsers();
		toSearchUser = new ApplicationUsers();
		this.selectedUser = new ApplicationUsers();
		this.modAliasObj = new LuModalityAlias();
		this.usersList = new ArrayList<ApplicationUsers>();
		this.selectedRolesList = new ArrayList<RolesApplAet>();
		this.modAliasList = new ArrayList<LuModalityAlias>();
		
		this.srcAetList = new ArrayList<String>();
		this.tgtAetList = new ArrayList<String>();
		this.srcModList = new ArrayList<String>();
		this.tgtModList = new ArrayList<String>();
		this.aetRolesDual = new DualListModel<String>(srcAetList, tgtAetList);
		this.modRolesDual = new DualListModel<String>(srcModList, tgtModList);
		
		
	}
	
	public String addNewUser()
	{
		
		AdminBll bll =new AdminBll();
		System.out.println("roles list size="+this.selectedRolesList.size());
		if(toAddUser==null || toAddUser.getUserId()==null || toAddUser.getUserId().trim().length()<1)
		{
			MessageUtils.error(MessageConstants.Messages.INVALID_USERNAME);
			return "";
		}
		
		if(bll.addNewUser(toAddUser,this.selectedRolesList,this.selectedModalitiesList))
		{
//			FacesUtils.addInfoMessage("Login credentials", MessageConstants.Messages.SAVE_SUCCESS);
			MessageUtils.info(MessageConstants.Messages.SAVE_SUCCESS);
			this.toAddUser = new ApplicationUsers();
			this.selectedRolesList.clear();
		}
		else
		{
//			FacesUtils.addErrorMessage("Login credentials", MessageConstants.Messages.SAVE_FAILURE);
			MessageUtils.error(MessageConstants.Messages.SAVE_FAILURE);
		}
		
		return "";
	}

	public String searchUsers()
	{
//		if(this.usersList.size()==0)
		{
			
			AdminBll bll =new AdminBll();
			this.usersList = bll.searchAllUser(toSearchUser);			

		}
		return "";
	}
	
	public void searchModAlias(ActionEvent e)
	{
		AdminBll bll =new AdminBll();
		this.modAliasList.clear();
		this.modAliasList = bll.searchModAliasList(this.modAliasObj);
	}
	
	
	public void updateModAlias(ActionEvent e)
	{

		AdminBll bll =new AdminBll();
		if(bll.updateModAliasList(this.modAliasList))
		{
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.modAliasList = bll.searchModAliasList(modAliasObj);	
			
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		
	}
	
	public void populateModAlias(ActionEvent e)
	{

		AdminBll bll =new AdminBll();
		System.out.println("In populateModAlias Method");
		if(bll.populateModAliasList())
		{
			System.out.println("populateModAlias Method executed successfully");
		}
		else
		{
			System.out.println("populateModAlias Method execution failed");
		}
		

		
	}
	
	public String testMethod()
	{
		System.out.println("tgt aet list elmns method");
		for(String s:aetRolesDual.getTarget())
		{
			System.out.println("tgt aet list elmns ="+s);
		}
		return "";
	}
	
//	Not being used now---- see updateRoles()
	public String updateUsers()
	{

		AdminBll bll =new AdminBll();
		if(usersList.size()>0 &&  bll.updateUsers(usersList))
		{
//			FacesUtils.addInfoMessage(MessageConstants.Messages.UPDATE_SUCCESS);
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.usersList = bll.searchAllUser(toSearchUser);			
		}
		else
		{
//			FacesUtils.addErrorMessage(MessageConstants.Messages.UPDATE_FAILURE);
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	public String updateRoles()
	{

		AdminBll bll =new AdminBll();
		if(bll.updateRoles(selectedUser,this.aetRolesDual,this.modRolesDual))
		{
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.usersList = bll.searchAllUser(toSearchUser);	
			return "manageUsers";
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	public String updateRolesFromDialog()
	{

		AdminBll bll =new AdminBll();
		if(bll.updateRoles(selectedUser,this.aetRolesDual,this.modRolesDual))
		{
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.usersList = bll.searchAllUser(toSearchUser);	
			
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	public String resetPassword()
	{
		AdminBll bll =new AdminBll();
		newPassword = Environment.getDefaultPassword();
		if(bll.changePassword(toSearchUser,newPassword))
		{
//			FacesUtils.addInfoMessage("Login credentials", MessageConstants.Messages.UPDATE_SUCCESS);
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			return NavigationConstants.ADMIN_MANAGE_USERS_NAVIGATION;			
		}
		else
		{
//			FacesUtils.addErrorMessage("Login credentials", MessageConstants.Messages.UPDATE_FAILURE);
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	public String changePassword()
	{
		currentUser = ((UserBean)FacesUtils.getManagedBean("userBean")).getCurrentUser();
		AdminBll bll =new AdminBll();
		
		if(newPassword==null || newPasswordAgain==null || 
				newPassword.trim().length()==0 	|| newPasswordAgain.trim().length()==0
				)
		{
//			FacesUtils.addErrorMessage("Login credentials", "Invalid Password");
			MessageUtils.error(MessageConstants.Messages.INVALID_PASSWORD);
			return "";
		}
		
		if(!newPassword.equals(newPasswordAgain))
		{
			MessageUtils.error("Passwords do not match");
			return "";
		}
		
		if(bll.changePassword(currentUser,newPassword))
		{
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			return "";//NavigationConstants.HOME_NAVIGATION;			
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	public String navigateToManageDetails()
	{
		
		populateAetTargetList();
		populateModTargetList();
		
		populateAetSourceList();
		populateModSourceList();
		
		
		this.aetRolesDual = new DualListModel<String>(srcAetList, tgtAetList);
		this.modRolesDual = new DualListModel<String>(srcModList, tgtModList);
		
		return NavigationConstants.ADMIN_MANAGE_USERS_DETAILS_NAVIGATION;
	}
	
	public String popUpManageDetailsDialog()
	{
		
		populateAetTargetList();
		populateModTargetList();
		
		populateAetSourceList();
		populateModSourceList();
		
		
		this.aetRolesDual = new DualListModel<String>(srcAetList, tgtAetList);
		this.modRolesDual = new DualListModel<String>(srcModList, tgtModList);
		
		return "";
	
	}
	
	private void populateAetSourceList()
	{		
		this.srcAetList=new ArrayList<String>();
		this.srcAetList.clear();
		for(SelectItem i:crt.getSrcAetList())
		{
			if(!this.tgtAetList.contains(i.getLabel()))
			{
				this.srcAetList.add(i.getLabel());
			}
		}
		
	}
	private void populateAetTargetList()
	{		
		this.tgtAetList=new ArrayList<String>();
		this.tgtAetList.clear();
		for(RolesApplAet i:this.selectedUser.getRolesAetFk())
		{
			this.tgtAetList.add(i.getUserRoleAet());
		}
	}
	private void populateModSourceList()
	{		
		this.srcModList = new ArrayList<String>();
		this.srcModList.clear();
		for(SelectItem i:crt.getModalityList())
		{
			if(!this.tgtModList.contains(i.getLabel()))
			{
				this.srcModList.add(i.getLabel());
			}
			
		}
		
	}
	private void populateModTargetList()
	{		
		this.tgtModList=new ArrayList<String>();
		this.tgtModList.clear();
		for(RolesApplModality i:this.selectedUser.getRolesModFk())
		{
			this.tgtModList.add(i.getUserRoleModality());
		}
	}	
	public ApplicationUsers getToAddUser() {
		return toAddUser;
	}

	public void setToAddUser(ApplicationUsers toAddUser) {
		this.toAddUser = toAddUser;
	}

	public List<ApplicationUsers> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<ApplicationUsers> usersList) {
		this.usersList = usersList;
	}

	public ApplicationUsers getToSearchUser() {
		return toSearchUser;
	}

	public void setToSearchUser(ApplicationUsers toSearchUser) {
		this.toSearchUser = toSearchUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}

	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}
	public String getScannedFileType() {
		return scannedFileType;
	}

	public void setScannedFileType(String scannedFileType) {
		this.scannedFileType = scannedFileType;
	}

	public List<RolesApplAet> getSelectedRolesList() {
		return selectedRolesList;
	}

	public void setSelectedRolesList(List<RolesApplAet> selectedRolesList) {
		this.selectedRolesList = selectedRolesList;
	}

	public List<RolesApplModality> getSelectedModalitiesList() {
		return selectedModalitiesList;
	}

	public void setSelectedModalitiesList(
			List<RolesApplModality> selectedModalitiesList) {
		this.selectedModalitiesList = selectedModalitiesList;
	}

	public ApplicationUsers getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(ApplicationUsers selectedUser) {
		this.selectedUser = selectedUser;
	}

	public DualListModel<String> getAetRolesDual() {
		return aetRolesDual;
	}

	public void setAetRolesDual(DualListModel<String> aetRolesDual) {
		this.aetRolesDual = aetRolesDual;
	}

	public DualListModel<String> getModRolesDual() {
		return modRolesDual;
	}

	public void setModRolesDual(DualListModel<String> modRolesDual) {
		this.modRolesDual = modRolesDual;
	}

	public LuModalityAlias getModAliasObj() {
		return modAliasObj;
	}

	public void setModAliasObj(LuModalityAlias modAliasObj) {
		this.modAliasObj = modAliasObj;
	}

	public List<LuModalityAlias> getModAliasList() {
		return modAliasList;
	}

	public void setModAliasList(List<LuModalityAlias> modAliasList) {
		this.modAliasList = modAliasList;
	}
	
}
