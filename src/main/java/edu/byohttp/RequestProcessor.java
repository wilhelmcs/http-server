package edu.byohttp;

import edu.byohttp.response.Response;

interface RequestProcessor {
    Response getAResponse(String requestStr);
}
