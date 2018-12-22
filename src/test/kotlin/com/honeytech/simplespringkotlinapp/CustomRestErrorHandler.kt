package com.honeytech.simplespringkotlinapp

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import java.io.IOException
import javax.xml.ws.http.HTTPException

class CustomRestErrorHandler : ResponseErrorHandler {

    @Throws(IOException::class)
    override fun handleError(response : ClientHttpResponse)
    {
        if (response.statusCode != HttpStatus.UNAUTHORIZED)
        {
            throw HTTPException(response.statusCode.value())

        }
    }


    override fun hasError(response : ClientHttpResponse) : Boolean
    {
        return response.statusCode.series() == HttpStatus.Series.CLIENT_ERROR
                || response.statusCode.series() == HttpStatus.Series.SERVER_ERROR
    }
}