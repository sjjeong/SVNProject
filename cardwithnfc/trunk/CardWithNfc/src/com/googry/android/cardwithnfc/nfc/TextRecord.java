package com.googry.android.cardwithnfc.nfc;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import android.nfc.NdefRecord;
import android.util.Log;

import com.google.common.base.Preconditions;

public class TextRecord implements ParsedRecord {

	/** ISO/IANA language code */
	private final String mLanguageCode;
	private final String mText;

	private TextRecord(String languageCode, String text) {
		Log.i("NFC", "TextRecord TextRecord()");
		mLanguageCode = Preconditions.checkNotNull(languageCode);
		mText = Preconditions.checkNotNull(text);
	}

	public int getType() {

		Log.i("NFC", "TextRecord getType()");
		return ParsedRecord.TYPE_TEXT;
	}

	public String getText() {
		Log.i("NFC", "TextRecord getText()");
		return mText;
	}

	/**
	 * Returns the ISO/IANA language code associated with this text element.
	 */
	public String getLanguageCode() {
		Log.i("NFC", "TextRecord getLanguageCode()");
		return mLanguageCode;
	}

	public static TextRecord parse(NdefRecord record) {
		Log.i("NFC", "TextRecord parse()");
		Preconditions
				.checkArgument(record.getTnf() == NdefRecord.TNF_WELL_KNOWN);
		Preconditions.checkArgument(Arrays.equals(record.getType(),
				NdefRecord.RTD_TEXT));
		try {
			byte[] payload = record.getPayload();
			String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8"
					: "UTF-16";
			int languageCodeLength = payload[0] & 0077;
			String languageCode = new String(payload, 1, languageCodeLength,
					"US-ASCII");
			String text = new String(payload, languageCodeLength + 1,
					payload.length - languageCodeLength - 1, textEncoding);
			return new TextRecord(languageCode, text);
		} catch (UnsupportedEncodingException e) {
			// should never happen unless we get a malformed tag.
			throw new IllegalArgumentException(e);
		}
	}

	public static boolean isText(NdefRecord record) {
		Log.i("NFC", "TextRecord isText()");
		try {
			parse(record);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}