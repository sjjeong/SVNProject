package com.googry.android.cardwithnfc.nfc;

import java.util.ArrayList;
import java.util.List;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.util.Log;

public class NdefMessageParser {
	private NdefMessageParser() {
		Log.i("NFC", "NdefMessageParser NdefMEssageParser()");
	}

	public static List<ParsedRecord> parse(NdefMessage message) {
		Log.i("NFC", "NdefMessageParser parse(NdefMessage message)");
		return getRecords(message.getRecords());
	}

	public static List<ParsedRecord> getRecords(NdefRecord[] records) {
		Log.i("NFC", "NdefMessageParser getRecords(NdefRecord[] records)");
		List<ParsedRecord> elements = new ArrayList<ParsedRecord>();
		for (NdefRecord record : records) {
			if (UriRecord.isUri(record)) {
				elements.add(UriRecord.parse(record));
			} else if (TextRecord.isText(record)) {
				elements.add(TextRecord.parse(record));
			}
		}
		return elements;
	}
}