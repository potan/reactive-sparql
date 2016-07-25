package com.modelfabric.sparql.api

sealed trait SparqlClientProtocol

/**
  * Request
  */
trait Request extends SparqlClientProtocol

/**
  * Represents a single Sparql request to be sent to the triple store.
  *
  * @param statement the sparql statement string, any | margins will be stripped automatically
  */
case class SparqlRequest(statement: SparqlStatement) extends Request

/**
  * "Is-Alive" request
  */
// JC: not used
case object PingRequest extends Request

/**
  * Response
  */
trait Response extends SparqlClientProtocol

/**
  * Represents a response from the triple store for a Sparql request.
  *
  * @param request the underlying request object is returned with the response
  * @param success true if the sparql statement execution succeeded
  * @param result
  * @param error
  */
case class SparqlResponse(
  request: SparqlRequest,
  success: Boolean = true,
  result: List[SparqlResult] = Nil,
  error: Option[SparqlClientError] = None) extends Response

/**
  * "Is-Alive" response
  *
  * @param success
  */
// JC: not used
case class PingResponse(success: Boolean = true) extends Response


/**
  * Error messages
  */
sealed trait SparqlClientError
case class SparqlClientRequestFailed(message: String) extends RuntimeException(message) with SparqlClientError
case class SparqlClientRequestFailedWithError(message: String, throwable: Throwable) extends RuntimeException(message, throwable) with SparqlClientError
