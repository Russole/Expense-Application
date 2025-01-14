import * as yup from 'yup';

const loginValidationSchema = yup.object().shape({
    email: yup.string().required('Email is required').email('Email is not valid'),
    password: yup.string().required('Password is required')
})

export default loginValidationSchema;