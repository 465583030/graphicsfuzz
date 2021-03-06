/*
 * Copyright 2018 The GraphicsFuzz Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.graphicsfuzz.common.ast.type;

import com.graphicsfuzz.common.ast.decl.ArrayInfo;
import com.graphicsfuzz.common.ast.expr.Expr;
import com.graphicsfuzz.common.ast.visitors.IAstVisitor;

public class ArrayType extends Type {

  private Type baseType;
  private ArrayInfo arrayInfo;

  public ArrayType(Type baseType, ArrayInfo arrayInfo) {
    this.baseType = baseType;
    this.arrayInfo = arrayInfo;
  }

  public Type getBaseType() {
    return baseType;
  }

  public ArrayInfo getArrayInfo() {
    return arrayInfo;
  }

  @Override
  public void accept(IAstVisitor visitor) {
    visitor.visitArrayType(this);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof ArrayType)) {
      return false;
    }
    ArrayType thatArrayType = (ArrayType) that;
    return this.baseType.equals(thatArrayType.baseType)
        && this.arrayInfo.equals(thatArrayType.arrayInfo);
  }

  @Override
  public int hashCode() {
    // TODO: revisit if we end up storing large sets of types
    return baseType.hashCode() + arrayInfo.hashCode();
  }

  @Override
  public ArrayType clone() {
    return new ArrayType(baseType.clone(), arrayInfo.clone());
  }

  @Override
  public boolean hasCanonicalConstant() {
    return false;
  }

  @Override
  public Expr getCanonicalConstant() {
    throw new RuntimeException("No canonical constant for ArrayType");
  }

  @Override
  public Type getWithoutQualifiers() {
    return this;
  }

  @Override
  public boolean hasQualifier(TypeQualifier qualifier) {
    return false;
  }

}
