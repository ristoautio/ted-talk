package com.example.tedtalk.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Talk not found")
public class TalkNotFoundException extends RuntimeException { }
