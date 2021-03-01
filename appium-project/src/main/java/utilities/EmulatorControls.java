package utilities;



import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class EmulatorControls {
	static String emulatorPath;
	static String adbPath;
	static String HomeDirectory;
	static String sdkPath;

	static BufferedReader inputStream;
	static Process process;

	static {

		//HomeDirectory = System.getProperty("user.home");
		HomeDirectory = "C:\\Users\\ckumar84";
		sdkPath = "AppData\\Local\\Android\\";
		emulatorPath = HomeDirectory + File.separator + sdkPath + "emulator\\emulator.exe";
		adbPath = HomeDirectory + File.separator + sdkPath + "platform-tools\\adb.exe";

	}

	private EmulatorControls() {
	}

	public static void launchEmulator(String nameOfAVD) {

		if (isEmulatorOrDeviceRunning()) {
			System.out.println("Emulator/Device is already running ..!!");
			return;

		} else {
			System.out.println("Starting emulator ->> " + nameOfAVD);
			String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
			try {
				process = new ProcessBuilder(aCommand).start();
				process.waitFor(40, TimeUnit.SECONDS);
				if (isEmulatorOrDeviceRunning())
					System.out.println("Emulator ->> " + nameOfAVD + " launched successfully!");
				else
					throw new Exception("Emulator ->> " + nameOfAVD + " could not be launched");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void closeEmulator() {
		if (isEmulatorOrDeviceRunning()) {
			System.out.println("Killing emulator...");
			String[] aCommand = new String[] { adbPath, "emu", "kill" };
			try {
				process = new ProcessBuilder(aCommand).start();
				process.waitFor(1, TimeUnit.SECONDS);
				if (!isEmulatorOrDeviceRunning())
					System.out.println("Emulator closed successfully .. !!");
				else if (isEmulatorOrDeviceRunning())
					System.err.println("Emulator could not be closed .. !!");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Currently there are no Emulators running");
	}

	public static boolean isEmulatorOrDeviceRunning() {
		String[] commandDevices;
		boolean isRunning = false;
		String output = "";
		String line = null;
		try {
			commandDevices = new String[] { adbPath, "devices" };
			process = new ProcessBuilder(commandDevices).start();
			inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((line = inputStream.readLine()) != null) {
				output = output + line;
			}
			if (!output.replace("List of devices attached", "").trim().equals("")) {
				isRunning = true;
				System.out.println("List of devices attached" + "\n" + output.replace("List of devices attached", ""));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isRunning;
	}

}
