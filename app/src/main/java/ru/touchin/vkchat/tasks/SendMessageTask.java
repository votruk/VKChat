package ru.touchin.vkchat.tasks;

import org.zuzuk.tasks.base.AbstractTask;

import ru.touchin.vkchat.models.SendMessageResponse;

public class SendMessageTask extends AbstractTask<SendMessageResponse> {
	public SendMessageTask(Class<SendMessageResponse> clazz) {
		super(clazz);
	}

	@Override
	public SendMessageResponse execute() throws Exception {
		return null;
	}
}
