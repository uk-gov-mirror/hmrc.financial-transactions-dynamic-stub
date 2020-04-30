/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mocks

import com.github.fge.jsonschema.main.JsonSchema
import org.mockito.ArgumentMatchers
import org.mockito.Mockito._
import play.api.libs.json.JsValue
import testUtils.TestSupport
import utils.SchemaValidation

import scala.concurrent.Future

trait MockSchemaValidation extends TestSupport {

  lazy val mockSchemaValidation: SchemaValidation = mock[SchemaValidation]

  def mockLoadResponseSchema(schemaId: String)(response: JsonSchema): Unit =
    when(mockSchemaValidation.loadResponseSchema(ArgumentMatchers.eq(schemaId)))
      .thenReturn(Future.successful(response))

  def mockValidateResponseJson(schemaId: String, json: Option[JsValue])(response: Future[Boolean]): Unit =
    when(mockSchemaValidation.validateResponseJson(ArgumentMatchers.eq(schemaId), ArgumentMatchers.eq(json)))
      .thenReturn(response)

  def mockLoadUrlRegex(schemaId: String)(response: String): Unit =
    when(mockSchemaValidation.loadUrlRegex(ArgumentMatchers.eq(schemaId)))
      .thenReturn(Future.successful(response))

  def mockValidateUrlMatch(schemaId: String, url: String)(response: Boolean): Unit =
    when(mockSchemaValidation.validateUrlMatch(ArgumentMatchers.eq(schemaId), ArgumentMatchers.eq(url)))
      .thenReturn(Future.successful(response))
}
