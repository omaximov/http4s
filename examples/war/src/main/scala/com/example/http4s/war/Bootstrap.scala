/*
 * Copyright 2014 http4s.org
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

package com.example.http4s
package war

import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.std.Dispatcher
import org.http4s.servlet.syntax._

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

@WebListener
class Bootstrap extends ServletContextListener {
  override def contextInitialized(sce: ServletContextEvent): Unit = {
    val app = new IOApp {
      override def run(args: List[String]): IO[ExitCode] = Dispatcher[IO]
        .use(dispatcher =>
          IO(
            sce.getServletContext
              .mountService("example", ExampleService[IO].routes, dispatcher = dispatcher)))
        .as(ExitCode.Success)
    }
    app.main(Array.empty)
  }

  override def contextDestroyed(sce: ServletContextEvent): Unit = {}
}
