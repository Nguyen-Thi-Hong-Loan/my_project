package lab;

import com.chilkatsoft.CkGlobal;
import com.chilkatsoft.CkScp;
import com.chilkatsoft.CkSsh;

public class DownloadBySCP {
	static {
		try {
			System.loadLibrary("chilkat");
//			System.loadLibrary("chilkat");

		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

	public static void download(String hostName, int port, String userName, String pass) {
		CkSsh ssh = new CkSsh();
		// Hostname may be an IP address or hostname:
//		 String hostname = "www.some-ssh-server.com";
//		 int port = 22;
		CkGlobal ck = new CkGlobal();
		ck.UnlockBundle("Hi");
		// Connect to an SSH server:
		boolean success = ssh.Connect(hostName, port);
		if (!success) {
			System.out.println(ssh.lastErrorText());
			return;
		}

		// Wait a max of 5 seconds when reading responses..
		ssh.put_IdleTimeoutMs(5000);
		// Authenticate using login/password:
		success = ssh.AuthenticatePw(userName, pass);
		if (!success) {
			System.out.println(ssh.lastErrorText());
			return;
		}
		CkScp scp = new CkScp();
		success = scp.UseSsh(ssh);
		if (!success) {
			System.out.println(ssh.lastErrorText());
			return;
		}
		scp.put_SyncMustMatch("sinhvien_chieu*.*");

		// This downloads a file from the "testApp/logs/" subdirectory (relative to the
		// SSH user account's HOME directory).
		// For example, if the HOME directory is /Users/chilkat, then the following
		// downloads
		// /Users/chilkat/testApp/logs/test1.log

		String remotePath = "/volume1/ECEP/song.nguyen/DW_2020/data";
		String localPath = "E:\\Tai_Lieu\\HK2-----3\\DatawareHouse\\FILE";
		
		success = scp.SyncTreeDownload(remotePath, localPath, 2, false);
		if (!success) {
			System.out.println(ssh.lastErrorText());
			return;
		}

		System.out.println("SCP download file success.");
		ssh.Disconnect();

	}

	public static void main(String[] args) {
		download("drive.ecepvn.org", 2227, "guest_access", "123456");

	}
}
