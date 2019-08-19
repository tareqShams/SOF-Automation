package fawry.sofAutomation.dbVerification.basicDefinitions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fawry.sofAutomation.pojos.basicDefinitions.CSPAccountTypePojo;
import fawry.sofAutomation.utils.DatabaseConnection;

public class CspAccountTypesVerifications {

	public CSPAccountTypePojo CspAccount(CSPAccountTypePojo cspAccountObject ) throws SQLException 
	{
		CSPAccountTypePojo acountType=new CSPAccountTypePojo();

		DatabaseConnection conn= new DatabaseConnection();
		Connection myconection=conn.openConnection();
		StringBuilder queryCond = new StringBuilder();
		queryCond.append("SELECT C.CODE AS CSP,AT.CODE AS ACCOUNT_TYPE ,btc.NAME_PRIMARY_LANG as Balance_BTC ,"
				+ " btcp.NAME_PRIMARY_LANG as Payment_BTC ,amp.CODE as ACTIVATION_MTHD_PARAM ,"
				+ " acc.ACCOUNT_CODE_PREFIX as AccountCodePrefix , acc.CANCEL_EXTREME_BALANCE_AMOUNT as CancelExtremeBalanceAmount,"
				+ " acc.MAX_ACCT_NUMBER as MaxAccountNumber,acc.EXCLUDED_BTCS  as ExcludedBtcs,"
				+ " acc.ADD_ENABLED as addEnabled,acc.DELETE_ENABLED as DeletedEnabled,acc.FUND_LOAD_ENABLED as FundLoadEnabled, "
				+ " acc.BALANCE_INQ_ENABLED as BALANCE_INQ_ENABLED ,acc.PIN_REQUIRED as PIN_REQUIRED, "
				+ " acc.ALLOW_ANONYMOUS_ACCOUNTS as ALLOW_ANONYMOUS_ACCOUNTS , acc.THREE_DS_SECURE_ENABLED as THREE_DS_SECURE_ENABLED "
				+ " ,acc.UPDATE_ENABLED as UPDATE_ENABLED , acc.CASH_OUT_ENABLED as CASH_OUT_ENABLED ,acc.CREATE_ENABLED as CREATE_ENABLED , "
				+ " acc.PMT_ENABLED as PMT_ENABLED ,acc.KYC_REQUIRED as KYC_REQUIRED , acc.DECRYPT_ACCT_NUM_PARAMETER as DECRYPT_ACCT_NUM_PARAMETER,"
				+ " acc.PURCHASE_ENABLE as PURCHASE_ENABLE , amp.CODE  as Add_ActivationMethodParameter, aaml.CODE as ActivationMethod,"
				+ " M.NAME_ENGLISH_LANG as Merchant, btcm.NAME_PRIMARY_LANG AS BTC, "
				+ " amp.EXT_AUTHORIZER_SYSTEM_CODE as ActivationAuthorizerSystemCode"
				+ " FROM POS_SOF.CSP_ACCT_TYPES_CONFIGURATION acc "
				+ " LEFT JOIN POS_SOF.CSP c on c.id= acc.CSP_ID "
				+ " LEFT JOIN POS_SOF.ACCOUNT_TYPES at on at.id=acc.ACCOUNT_TYPE_ID "
				+ "	LEFT JOIN POS_SOF.BTC btc on btc.id=acc.BALANCE_BTC_ID "
				+ "	LEFT JOIN POS_SOF.BTC btcp on btcp.id=acc.PAYMENT_BTC_ID "
				+ " LEFT JOIN POS_SOF.ACTIVATION_METHOD_PARAMETERS amp on amp.id=acc.ACTIVATION_MTHD_PARAM_ID "
				+ " LEFT JOIN POS_SOF.ACCT_ACTIVATION_METHOD_LOOKUP aaml on aaml.id=amp.ACTIVATION_METHOD_ID	"
				+ " LEFT JOIN POS_SOF.MERCHANTS m on m.id=amp.MERCHANT_ID "
				+ " LEFT JOIN POS_SOF.BTC btcm on btcm.id=amp.BTC_ID"
				+ " where c.CODE='"+cspAccountObject.getCsp()+"'"
				+ " AND at.CODE='"+cspAccountObject.getAccountType()+"'");

		System.out.println(queryCond.toString());

		ResultSet resultOfSearchaccount=conn.executeVerificationQuery(myconection, queryCond.toString());


		if(resultOfSearchaccount.next())
		{
			acountType.setCsp(resultOfSearchaccount.getString("CSP")==null?"":resultOfSearchaccount.getString("CSP"));
			acountType.setAccountType(resultOfSearchaccount.getString("ACCOUNT_TYPE")==null?"":resultOfSearchaccount.getString("ACCOUNT_TYPE"));
			acountType.setPaymentBTC(resultOfSearchaccount.getString("Payment_BTC")==null?"":resultOfSearchaccount.getString("Payment_BTC"));
			acountType.setBalanceBTC(resultOfSearchaccount.getString("Balance_BTC")==null?"":resultOfSearchaccount.getString("Balance_BTC"));
			acountType.setActivationMethodParameter(resultOfSearchaccount.getString("ACTIVATION_MTHD_PARAM")==null?"":resultOfSearchaccount.getString("ACTIVATION_MTHD_PARAM"));
			acountType.setAccountCodePrefix(resultOfSearchaccount.getString("AccountCodePrefix")==null?"":resultOfSearchaccount.getString("AccountCodePrefix"));
			acountType.setCancelExtremeBalanceAmount(resultOfSearchaccount.getString("CancelExtremeBalanceAmount")==null?"":resultOfSearchaccount.getString("CancelExtremeBalanceAmount"));
			acountType.setMaxNumberOfAccounts(resultOfSearchaccount.getString("MaxAccountNumber")==null?"":resultOfSearchaccount.getString("MaxAccountNumber"));
			acountType.setExcludedBTCS(resultOfSearchaccount.getString("ExcludedBtcs")==null?"":resultOfSearchaccount.getString("ExcludedBtcs"));
			acountType.setAddEnabled(resultOfSearchaccount.getString("addEnabled")==null||resultOfSearchaccount.getString("addEnabled").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("addEnabled"));
			acountType.setDeleteEnabled(resultOfSearchaccount.getString("DeletedEnabled")==null||resultOfSearchaccount.getString("DeletedEnabled").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("DeletedEnabled"));
			acountType.setFundLoadEnabled(resultOfSearchaccount.getString("FundLoadEnabled")==null||resultOfSearchaccount.getString("FundLoadEnabled").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("FundLoadEnabled"));
			acountType.setBalanceINQEnabled(resultOfSearchaccount.getString("BALANCE_INQ_ENABLED")==null||resultOfSearchaccount.getString("BALANCE_INQ_ENABLED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("BALANCE_INQ_ENABLED"));
			acountType.setPinRequired(resultOfSearchaccount.getString("PIN_REQUIRED")==null||resultOfSearchaccount.getString("PIN_REQUIRED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("PIN_REQUIRED"));
			acountType.setAllowAnonymousAccounts(resultOfSearchaccount.getString("ALLOW_ANONYMOUS_ACCOUNTS")==null||resultOfSearchaccount.getString("ALLOW_ANONYMOUS_ACCOUNTS").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("ALLOW_ANONYMOUS_ACCOUNTS"));
			acountType.setThreeDSecuredEnabled(resultOfSearchaccount.getString("THREE_DS_SECURE_ENABLED")==null||resultOfSearchaccount.getString("THREE_DS_SECURE_ENABLED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("THREE_DS_SECURE_ENABLED"));
			acountType.setUpdateEnabled(resultOfSearchaccount.getString("UPDATE_ENABLED")==null||resultOfSearchaccount.getString("UPDATE_ENABLED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("UPDATE_ENABLED"));
			acountType.setCashOutEnabled(resultOfSearchaccount.getString("CASH_OUT_ENABLED")==null||resultOfSearchaccount.getString("CASH_OUT_ENABLED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("CASH_OUT_ENABLED"));
			acountType.setCreateEnabled(resultOfSearchaccount.getString("CREATE_ENABLED")==null ||resultOfSearchaccount.getString("CREATE_ENABLED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("CREATE_ENABLED"));
			acountType.setPmtEnabled(resultOfSearchaccount.getString("PMT_ENABLED")==null||resultOfSearchaccount.getString("PMT_ENABLED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("PMT_ENABLED"));
			acountType.setKycRequired(resultOfSearchaccount.getString("KYC_REQUIRED")==null||resultOfSearchaccount.getString("KYC_REQUIRED").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("KYC_REQUIRED"));
			acountType.setDecryptAccountNumberParameter(resultOfSearchaccount.getString("DECRYPT_ACCT_NUM_PARAMETER")==null||resultOfSearchaccount.getString("DECRYPT_ACCT_NUM_PARAMETER").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("DECRYPT_ACCT_NUM_PARAMETER"));
			acountType.setPurchaseEnable(resultOfSearchaccount.getString("PURCHASE_ENABLE")==null||resultOfSearchaccount.getString("PURCHASE_ENABLE").equalsIgnoreCase("n")?"":resultOfSearchaccount.getString("PURCHASE_ENABLE"));
			acountType.setCode(resultOfSearchaccount.getString("Add_ActivationMethodParameter")==null?"":resultOfSearchaccount.getString("Add_ActivationMethodParameter"));
			acountType.setActivationMethod(resultOfSearchaccount.getString("ActivationMethod")==null?"":resultOfSearchaccount.getString("ActivationMethod"));
			acountType.setBtc(resultOfSearchaccount.getString("BTC")==null?"":resultOfSearchaccount.getString("BTC"));
			acountType.setMerchant(resultOfSearchaccount.getString("Merchant")==null?"":resultOfSearchaccount.getString("Merchant"));
			acountType.setActivationAuthorizerSystemCode(resultOfSearchaccount.getString("ActivationAuthorizerSystemCode")==null?"":resultOfSearchaccount.getString("ActivationAuthorizerSystemCode"));
			
		}
		else 
		{
			return null;
		}

		return acountType;
	}


}
